package com.example.a20bcm053lab4;
import java.util.*;

class ExpressionEvaluation{
    Stack<Integer> vals; Stack<Character> exprs;
    ExpressionEvaluation(){
        vals = new Stack<>();
        exprs = new Stack<>();
    }
    int preferences(char ch){
        switch (ch) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
            default:  return -1;
        }
    }
    boolean isOpt(char ch){
        return ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^';
    }
    int evalExp(){
        int a = vals.pop(),b=vals.pop();
        char expr = exprs.pop();
        switch (expr) {
            case '+': return a + b;
            case '-': return b - a;
            case '*': return a * b;
            case '^': return (int) Math.pow(a, b);
            case '/': return a > 0 ? (b / a) : Integer.MAX_VALUE;
            default: return 0;
        }
    }
    int expressionValue(String s){
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){int value=0;
                while (Character.isDigit(ch)) {
                    value = value * 10 + (ch - '0'); i++;
                    if (i < s.length()) ch = s.charAt(i);
                    else break;
                }
                vals.push(value);i--;
            }
            else if(ch=='('){exprs.push(ch);}
            else if(ch==')'){
                while(exprs.peek()!='(') {vals.push(evalExp());}
                exprs.pop();
            }
            else if(isOpt(ch)){
                while(!exprs.isEmpty()&&preferences(ch)<=preferences(exprs.peek())){vals.push(evalExp());}
                exprs.push(ch);
            }
        }
        while(!exprs.isEmpty()){vals.push(evalExp());}
        return vals.pop();
    }
}