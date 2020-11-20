'use strict';
Object.defineProperty(exports, "__esModule", { value: true });
exports.tokenize = exports.relativePath = void 0;
const vscode = require("vscode");
function relativePath(filePath) {
    return vscode.workspace.asRelativePath(filePath);
}
exports.relativePath = relativePath;
function tokenize(pragmaLine) {
    var ls = [];
    let pos = 0;
    var command = "";
    var parameters = "";
    while (pos < pragmaLine.length) {
        while (pos < pragmaLine.length && pragmaLine[pos] == ' ') {
            pos++;
        }
        //get the command until space or beginning
        while (pos < pragmaLine.length && (pragmaLine[pos] != '(' && pragmaLine[pos] != ' ')) {
            command += pragmaLine[pos];
            pos++;
        }
        if (pos < pragmaLine.length && pragmaLine[pos] == '(') {
            let open = 1;
            parameters += pragmaLine[pos];
            pos++;
            while (open != 0 && pos < pragmaLine.length) {
                parameters += pragmaLine[pos];
                if (pragmaLine[pos] == '(')
                    open++;
                if (pragmaLine[pos] == ')')
                    open--;
                ++pos;
            }
        }
        else
            parameters = "";
        let token_attr = { token: command, attr: parameters };
        ls.push(token_attr);
        command = "";
        parameters = "";
    }
    return ls;
}
exports.tokenize = tokenize;
//# sourceMappingURL=Utils.js.map