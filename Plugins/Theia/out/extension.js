"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.activate = void 0;
const vscode = require("vscode");
const CompletionItemProvider_1 = require("./CompletionItemProvider");
function activate(context) {
    vscode.languages.getLanguages().then((languages) => {
        console.log("language: " + languages.toString());
    });
    let providerPragma = vscode.languages.registerCompletionItemProvider('cpp', CompletionItemProvider_1.CompletionItemProvider);
    let providerPragma2 = vscode.languages.registerCompletionItemProvider('c', CompletionItemProvider_1.CompletionItemProvider);
    context.subscriptions.push(providerPragma);
    context.subscriptions.push(providerPragma2);
}
exports.activate = activate;
//# sourceMappingURL=extension.js.map