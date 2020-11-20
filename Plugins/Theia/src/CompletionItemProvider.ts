import * as vscode from 'vscode';

import { CompletionItem}  from  'vscode';
import { tokenize, Token_Attr } from './Utils';

import * as graph from './tree.json';
import * as directiveInfo from './tokens.json';
import * as fs from 'fs';
import * as path from 'path'
import { stringify } from 'querystring';



/**
 * Class that provides completion items for this extension.
 *
 * @class CompletionItemProviderClass
 */
class CompletionItemProviderClass {
  
    private readDocs(pragma:string):string
    {
        let pth = path.join(__dirname, "resources", pragma+".txt");
     
        if(!fs.existsSync(pth)) return "";
        return   fs.readFileSync(pth, "utf8")

    }
    private DirectiveRepeatable(directive:string)
    {
        let directives:any = directiveInfo;
        let dirinf = directives[directive];
        if(typeof dirinf == "undefined")
                return false;
            return dirinf.repeatable;
    }
   
    private HasExpression(directive:string)
    {
        let directives:any = directiveInfo;
        let dirinf = directives[directive];
        if(typeof dirinf == "undefined")
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
    provideCompletionItems(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken): vscode.ProviderResult<vscode.CompletionItem[] | vscode.CompletionList> {
        var ate = vscode.window.activeTextEditor;

        var line = "";
        if(ate!=null)
            line = document.lineAt(ate.selection.active.line).text;
        else return;

       // let line = document.lineAt(vscode.window.activeTextEditor.selection.active.line).text;
        let token_attr:Token_Attr[] = tokenize(line.trim());
        let only_tokens:string[] = [];

        /*This ensure args erase*/
        token_attr.forEach(element => {
            
            if(element.token.indexOf('(') != -1)
                only_tokens.push(element.token.substring(0, element.token.indexOf('(')))
            else if(element.token.indexOf('=') != -1)
                only_tokens.push(element.token.substring(0, element.token.indexOf('=')))
            else only_tokens.push(element.token)
        });


        var status:any = graph;
        var currentToken = only_tokens[only_tokens.length-1];

        var all_valid_directives:string[] = [];
        var filtered_directives:string[] = [];
        
        
        let directives:any = directiveInfo;

        var incomplete_last_token = directives[currentToken] == undefined

        
        /*Get the context of the directives, tree-like structure*/
        for(var i=0; i<only_tokens.length;++i)
        {
        
            var st = status[only_tokens[i]]; 
            if ( typeof(st) !== 'undefined' && st !== null ) 
                status=st;

        }


        /*Get all applicable directives */
        for(var obj in status){
            if(obj.toString()=="defaults")
                for(var def in status.defaults)
                    all_valid_directives.push(status.defaults[def].toString());
            else    all_valid_directives.push(obj.toString());

        }
        
        /*Only show the directives that matches user input*/
        all_valid_directives.forEach(elem => {
            if(incomplete_last_token && elem.startsWith(currentToken)) filtered_directives.push(elem);
            else if(elem.length==0) {}
            else filtered_directives.push(elem);
        });


        /*Remove directives that are not repeatable and the user already used.*/
        only_tokens.forEach(tok => {
                if (!this.DirectiveRepeatable(tok)) 
                {
                    for(let i=0; i<filtered_directives.length; ++i)
                    {
                        if(filtered_directives[i]===tok)
                        {
                            filtered_directives.splice(i, 1);
                            break;
                        }
                    }
            }
    	});

        
        /*Create autocomplete array*/
        let isHlsPragma = -1!=only_tokens.findIndex( (st:String)=>{ return st == "HLS"});
        let cItemArray: Array<CompletionItem> = [];
        filtered_directives.forEach((its) => {
            let completionItem = null;

 
            if(its[0]=='#')
                 completionItem = new CompletionItem("pragma");
            else  completionItem = new CompletionItem(its);

            
            if(this.HasExpression(its))
            {
                if(isHlsPragma) completionItem.insertText = its+"=" 
                else
                {
                    completionItem.insertText = its+"()" 
                } 
            }

    
            
            cItemArray.push(completionItem);
        });

        return cItemArray;
    }
}

export const CompletionItemProvider = new CompletionItemProviderClass()
