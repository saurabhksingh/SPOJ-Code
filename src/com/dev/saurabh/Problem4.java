package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/12/12
 * Time: 12:06 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem4 {
    /**
     * Transform the algebraic expression with brackets into RPN form (Reverse Polish Notation).
     Two-argument operators: +, -, *, /, ^ (priority from the lowest to the highest), brackets ( ). Operands:
     only letters: a,b,...,z. Assume that there is only one RPN form (no expressions like a*b*c).
     */
    private static final Map<Character, Integer> operators = new HashMap<Character, Integer>();
    static
    {
        operators.put('+', 0);
        operators.put('-', 1);
        operators.put('*',2);
        operators.put('/',3);
        operators.put('^',4);
        operators.put('(',5);
    }
    //in increasing order of precedence
    public static void main(String [] args){

        StringBuilder out = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int totalExpressions = Integer.parseInt(br.readLine());
            if(totalExpressions > 0){

                for(int i=0; i<totalExpressions; i++){
                    String expression = br.readLine();
                    out.append(getRPNForm(expression)).append("\n");
                }
            }

            System.out.println(out.toString());
        }
        catch(Exception exc){
            exc.printStackTrace();
        }

    }

    private static String getRPNForm(String expression) {

        Stack<Character> expressionStack = new Stack<Character>();
        StringBuilder out = new StringBuilder();

        char [] inExpression = expression.toCharArray();
        for(char ch : inExpression){
            if('a'<=ch && ch<='z'){
                out.append(ch);
            }
            else {
                if(expressionStack.isEmpty())
                {
                    expressionStack.push(ch);
                }
                else
                {
                    if(ch == ')')
                    {
                        char temp = ' ';
                        while(!expressionStack.isEmpty() && ((temp = expressionStack.pop()) != '(')){
                            out.append(temp);
                        }
                        //expressionStack.pop();
                    }
                    else if(!expressionStack.isEmpty() && (operators.get(ch) >= operators.get(expressionStack.peek())) ||
                            operators.get(expressionStack.peek())==5)
                    {
                        expressionStack.push(ch);
                    }
                    else
                    {
                        while(!expressionStack.isEmpty() && (operators.get(ch) < (operators.get(expressionStack.peek())))){
                            out.append(expressionStack.pop());
                        }
                    }
                }
            }
        }

        return out.toString();
    }
}
