package plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.cdt.ui.text.contentassist.ContentAssistInvocationContext;
import org.eclipse.cdt.ui.text.contentassist.ICompletionProposalComputer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;

import org.json.*;

public class PragmaCompletionProposalComputer implements ICompletionProposalComputer {
//TODO: Class ContentAssistant

	protected static String getCurrentLine(final ContentAssistInvocationContext context) throws BadLocationException {
		IDocument document = context.getDocument();
		int lineNumber = document.getLineOfOffset(context.getInvocationOffset());
		IRegion lineInformation = document.getLineInformation(lineNumber);
		return document.get(lineInformation.getOffset(), context.getInvocationOffset() - lineInformation.getOffset());
	}

	@Override
	public void sessionStarted() {
		// TODO Auto-generated method stub

	}

	public String convert(InputStream inputStream, Charset charset) throws IOException {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
			return br.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			return "";
		}
	}

	private String getFile(String fileName) {

		String ret = "none";
		URL url;
		try {
			url = new URL("platform:/plugin/BSCPragmas/resources/" + fileName);
			InputStream inputStream = url.openConnection().getInputStream();
			ret = convert(inputStream, Charset.defaultCharset());

		} catch (IOException e) {
			// e.printStackTrace();
			ret = "not found";
		}

		return ret;

	}

	public boolean DirectiveRepeatable(String directive) {

		JSONObject token_json = new JSONObject(getFile("tokens.json"));

		try {
			JSONObject st = (JSONObject) token_json.get(directive);
			return st.getBoolean("repeatable");

		} catch (org.json.JSONException exception2) {

			return false;
		}

	}

	public static List<String> pragma2token(String pragma) {
		List<String> ls = new ArrayList<String>();

		int pos = 0;
		String block = "";
		while (pos < pragma.length()) {

			// System.out.println("block: "+block);
			char current = pragma.charAt(pos);
			if (current == ' ') {
				if (block.length() > 2)
					ls.add(block);
				block = "";
			} else if (current == '(') {
				int open = 1;
				block += pragma.charAt(pos);
				pos++;
				while (open != 0 && pos < pragma.length()) {
					char current2 = pragma.charAt(pos);
					block += current2;
					if (current2 == '(')
						open++;
					if (current2 == ')')
						open--;
					++pos;
				}

				if (pos >= pragma.length() || open == 0) {
					ls.add(block);
					block = "";
				}
			} else {
				block += pragma.charAt(pos);

			}
			++pos;
		}

		if (!block.equals(""))
			ls.add(block);

		return ls;

	}

	@Override
	public List<ICompletionProposal> computeCompletionProposals(ContentAssistInvocationContext context,	IProgressMonitor monitor) {
		
		int offset = context.getInvocationOffset();

		List<String> tokens = new ArrayList<String>();
		String current_line = "";
		try {
			current_line = getCurrentLine(context);
			tokens = pragma2token(current_line.trim());

		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		Boolean incomplete_last_token = false;
		String current_token = tokens.get(tokens.size() - 1);

		for (int i = 0; i < tokens.size(); ++i) 
		{
			if (tokens.get(i).indexOf('(') != -1) 
			{
				tokens.set(i, tokens.get(i).substring(0, tokens.get(i).indexOf("(")));
			}
			if (tokens.get(i).indexOf('=') != -1) 
			{
				tokens.set(i, tokens.get(i).substring(0, tokens.get(i).indexOf("=")));
			}

		}

		JSONObject obj = new JSONObject(getFile("tree.json"));
		JSONObject token_json = new JSONObject(getFile("tokens.json"));

		/* Get the context of the directives, tree-like structure */
		for (int i = 0; i < tokens.size(); ++i) {
			try {
				JSONObject st = (JSONObject) obj.get(tokens.get(i));
				if (st != null)
					obj = st;
			} catch (org.json.JSONException exception) {
				// this means that the token doesn't exists, is not really a problem.
				System.out.println(exception);
				try {
					JSONObject st = (JSONObject) token_json.get(tokens.get(i));

				} catch (org.json.JSONException exception2) {
					incomplete_last_token = true;
				}
			}
		}

		if (incomplete_last_token)
			tokens.remove(tokens.size() - 1);

		/* Get all applicable directives */
		ArrayList<String> all_valid_directives = new ArrayList<String>();
		ArrayList<String> filtered_directives = new ArrayList<String>();

		JSONArray names = obj.names();
		for (int i = 0; i < names.length(); ++i) {
			String name = names.getString(i);
			if (name.equals("defaults")) {
				JSONArray defs = (JSONArray) obj.get("defaults");
				for (int j = 0; j < defs.length(); ++j) {
					String current = defs.getString(j);
					all_valid_directives.add(current);
					System.out.println(current);
				}

			} else
				all_valid_directives.add(name);
		}

		/* Only show the directives that matches user input */
		for (String str : all_valid_directives) {
			if (incomplete_last_token) {

				if (str.startsWith(current_token))
					filtered_directives.add(str);
				else
					System.out.println("ERASED: " + str);
			} else
				filtered_directives.add(str);
		}

		/* Remove directives that are not repeatable and the user already used. */
		for (String str : tokens) {

			if (!DirectiveRepeatable(str)) {
				int idx = filtered_directives.indexOf(str);
				if (idx >= 0) {
					filtered_directives.remove(str);
				}
			}

		}

		boolean isHlsPragma = tokens.indexOf("HLS") != -1;

		/* Create autocomplete array */
		ArrayList<ICompletionProposal> cItemArray = new ArrayList<ICompletionProposal>();

		for (String fd : filtered_directives) {
			if(fd.isEmpty()) continue;
			String realreplacement = fd;
			CompletionProposal cp;

			int replacementOffset = offset;
			int replacementLenght = 0;
			if (incomplete_last_token) {
				int currentUserSize = current_token.length();
				replacementOffset = offset - currentUserSize;
				replacementLenght = currentUserSize;
			}

			JSONObject st = (JSONObject) token_json.get(fd);
			boolean isExpr = st.getBoolean("expression");
			if (isExpr) {
				if (isHlsPragma)
					realreplacement = realreplacement + "= ";
				else
					realreplacement = realreplacement + "()";
			}

			String autocompletionstring = "You can read the HLS Documentation from the official source: https://www.xilinx.com/html_docs/xilinx2020_1/vitis_doc/hlspragmas.html";
			
			if(!isHlsPragma) autocompletionstring = getFile(fd + ".txt");
			
			cp = new CompletionProposal(realreplacement, // replacementString
					replacementOffset, // replacementOffset
					replacementLenght, // replacementLength
					isExpr ? realreplacement.length() - 1 : realreplacement.length(), null, fd, // text to be displayed
					null, autocompletionstring );// additional Info

			cItemArray.add(cp);

		}

		System.out.println("Current Token: " + current_token);
		return cItemArray;
	}

	@Override
	public List<IContextInformation> computeContextInformation(ContentAssistInvocationContext context,
			IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sessionEnded() {
		// TODO Auto-generated method stub

	}

}
