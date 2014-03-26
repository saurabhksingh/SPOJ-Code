package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 2/10/14.
 */
public class WORDNUM2 {
    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            String input = consoleReader.readLine();
            boolean isNumber = input.matches("^[0-9]+$");
            if(isNumber){
                System.out.println("number");
            }
            else{
                System.out.println("word");
            }
        }
        catch(Exception exc){

        }
    }
}
