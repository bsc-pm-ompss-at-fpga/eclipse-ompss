
import * as vscode from 'vscode';
import { CompletionItemProvider } from './CompletionItemProvider';

export function activate(context: vscode.ExtensionContext) {
	vscode.languages.getLanguages().then((languages:any) => {

		console.log("language: "+languages.toString());
	});
	let providerPragma = vscode.languages.registerCompletionItemProvider('cpp', CompletionItemProvider);
	let providerPragma2 = vscode.languages.registerCompletionItemProvider('c', CompletionItemProvider);
	context.subscriptions.push(providerPragma);
	context.subscriptions.push(providerPragma2);
}
