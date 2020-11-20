"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CompletionItemProvider = void 0;
const vscode = require("vscode");
const vscode_1 = require("vscode");
const Utils_1 = require("./Utils");
const graph = require("./tree.json");
const directiveInfo = require("./tokens.json");
const fs = require("fs");
const path = require("path");
/**
 * Class that provides completion items for this extension.
 *
 * @class CompletionItemProviderClass
 */
class CompletionItemProviderClass {
    readDocs(pragma) {
        let pth = path.join(__dirname, "resources", pragma + ".txt");
        if (!fs.existsSync(pth))
            return "";
        return fs.readFileSync(pth, "utf8");
    }
    DirectiveRepeatable(directive) {
        let directives = directiveInfo;
        let dirinf = directives[directive];
        if (typeof dirinf == "undefined")
            return false;
        return dirinf.repeatable;
    }
    HasExpression(directive) {
        let directives = directiveInfo;
        let dirinf = directives[directive];
        if (typeof dirinf == "undefined")
            return false;
        return dirinf.expression;
    }
    /**
     * Provides the completion items for the supplied words.
     *
     * @param {TextDocument} document
     * @param {Position} position
     * @param {CancellationToken} token
     * @returns
     */
    provideCompletionItems(document, position, token) {
        var ate = vscode.window.activeTextEditor;
        var line = "";
        if (ate != null)
            line = document.lineAt(ate.selection.active.line).text;
        else
            return;
        // let line = document.lineAt(vscode.window.activeTextEditor.selection.active.line).text;
        let token_attr = Utils_1.tokenize(line.trim());
        let only_tokens = [];
        /*This ensure args erase*/
        token_attr.forEach(element => {
            if (element.token.indexOf('(') != -1)
                only_tokens.push(element.token.substring(0, element.token.indexOf('(')));
            else if (element.token.indexOf('=') != -1)
                only_tokens.push(element.token.substring(0, element.token.indexOf('=')));
            else
                only_tokens.push(element.token);
        });
        var status = graph;
        var currentToken = only_tokens[only_tokens.length - 1];
        var all_valid_directives = [];
        var filtered_directives = [];
        let directives = directiveInfo;
        var incomplete_last_token = directives[currentToken] == undefined;
        /*Get the context of the directives, tree-like structure*/
        for (var i = 0; i < only_tokens.length; ++i) {
            var st = status[only_tokens[i]];
            if (typeof (st) !== 'undefined' && st !== null)
                status = st;
        }
        /*Get all applicable directives */
        for (var obj in status) {
            if (obj.toString() == "defaults")
                for (var def in status.defaults)
                    all_valid_directives.push(status.defaults[def].toString());
            else
                all_valid_directives.push(obj.toString());
        }
        /*Only show the directives that matches user input*/
        all_valid_directives.forEach(elem => {
            if (incomplete_last_token && elem.startsWith(currentToken))
                filtered_directives.push(elem);
            else if (elem.length == 0) { }
            else
                filtered_directives.push(elem);
        });
        /*Remove directives that are not repeatable and the user already used.*/
        only_tokens.forEach(tok => {
            if (!this.DirectiveRepeatable(tok)) {
                for (let i = 0; i < filtered_directives.length; ++i) {
                    if (filtered_directives[i] === tok) {
                        filtered_directives.splice(i, 1);
                        break;
                    }
                }
            }
        });
        /*Create autocomplete array*/
        let isHlsPragma = -1 != only_tokens.findIndex((st) => { return st == "HLS"; });
        let cItemArray = [];
        filtered_directives.forEach((its) => {
            let completionItem = null;
            if (its[0] == '#')
                completionItem = new vscode_1.CompletionItem("pragma");
            else
                completionItem = new vscode_1.CompletionItem(its);
            if (this.HasExpression(its)) {
                if (isHlsPragma)
                    completionItem.insertText = its + "=";
                else {
                    completionItem.insertText = its + "()";
                }
            }
            cItemArray.push(completionItem);
        });
        return cItemArray;
    }
}
exports.CompletionItemProvider = new CompletionItemProviderClass();
//# sourceMappingURL=CompletionItemProvider.js.map