package utils;

public class exprChecker 
{
	
	private static boolean variableHasIllegalCharacters(String check)
	{
		String pattern = "^[a-zA-Z_][a-zA-Z0-9_]*$";
		return !check.matches(pattern);
	}
	
	
	private static boolean memoryReferenceHasIllegalCharacters(String check)
	{
		return true;
		/*
		String pattern = "^[*&a-zA-Z_][a-zA-Z0-9_]*$";
		return !check.matches(pattern);*/
	}
	
	private static final String[] special_check_memory_reference_list = {"(in:","(inout:" ,"(out:","(concurrent:","(commutative:"};
	public static boolean check_memory_reference_list(String expr)
	{
		for(String str : special_check_memory_reference_list)
		{
			if(expr.startsWith(str))
			{
				expr = "("+expr.substring(str.length());
				break;
			}
		}
		
		int commaCount = 0;
		for(int i=0; i<expr.length(); ++i) if(expr.charAt(i)==',') ++commaCount;
		
		
		String[] subs = expr.substring(1, expr.length()-1).split(",");
		
		if(commaCount!=subs.length-1) return false;
		
		for(String svar : subs)
		{
			if(memoryReferenceHasIllegalCharacters(svar))
			{
				return false;
			}
		}

		return true;
		
	}

	public static boolean check_list(String expr)
	{
		int commaCount = 0;
		for(int i=0; i<expr.length(); ++i) if(expr.charAt(i)==',') ++commaCount;
		
		
		String[] subs = expr.substring(1, expr.length()-1).split(",");
		
		if(commaCount!=subs.length-1) return false;
		
		for(String svar : subs)
		{
			if(variableHasIllegalCharacters(svar))
			{
				return false;
			}
		}

		return true;
	}

	public static boolean check_expression(String expr)
	{
		int open = 0;
		for(int i=0; i<expr.length(); ++i) 
		{
			if(expr.charAt(i)=='(') ++open;
			else if(expr.charAt(i)==')') --open;
		}
		return open==0;	
	}
	
	
	public static boolean check_scalar_expression(String expr)
	{
		int open = 0;
		for(int i=0; i<expr.length(); ++i) 
		{
			if(expr.charAt(i)=='(') ++open;
			else if(expr.charAt(i)==')') --open;
		}
		return open==0;	
	}
	
	

	public static boolean check_string(String expr)
	{
		expr.substring(1, expr.length()-1);
		return expr.charAt(1)==expr.charAt(expr.length()-1) && expr.charAt(1) == '"';	
	}
	

}
