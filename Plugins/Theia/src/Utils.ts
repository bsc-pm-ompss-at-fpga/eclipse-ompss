'use strict';
import * as vscode from 'vscode';



export function relativePath(filePath: string) {
    return vscode.workspace.asRelativePath(filePath);
}

export interface Token_Attr
{
    token:string;
    attr:string;
}
export function tokenize(pragmaLine: string):Token_Attr[]
{
   
        var ls:Token_Attr[] = [];
		let pos = 0;
		var command = "";
		var parameters = "";
		while(pos<pragmaLine.length)
		{
            while(pos<pragmaLine.length && pragmaLine[pos]==' ')
            {
                pos++;
            }

            //get the command until space or beginning
            while(pos<pragmaLine.length && (pragmaLine[pos]!='(' && pragmaLine[pos]!=' '))
            {
                command+=pragmaLine[pos];
                pos++;
            }

            if(pos<pragmaLine.length && pragmaLine[pos]=='(')
            {
                let open = 1;
                parameters += pragmaLine[pos];
                pos++;
                while(open!=0 && pos<pragmaLine.length)
                {
                    parameters += pragmaLine[pos];
					if( pragmaLine[pos]=='(') open++;
                    if( pragmaLine[pos]==')') open--;
                    ++pos;
                }
            }
            else parameters = "";
            let token_attr:Token_Attr = {token: command, attr: parameters};

            ls.push(token_attr);
            command="";
            parameters="";

        }
		return ls;
		
}

