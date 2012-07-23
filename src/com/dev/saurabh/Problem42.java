package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/23/12
 * Time: 1:49 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem42 {

    public static void main(String [] arguments)
    {
        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int numberOfTestCases = Integer.parseInt(consoleReader.readLine());
            StringBuilder out = new StringBuilder();

            for(int i=numberOfTestCases; i>0; i--)
            {
                String input = consoleReader.readLine();
                String [] args = input.split(" ");
                String first = "";
                String second = "";
                if(args[0].length() > args[1].length())
                {
                   first = args[1];
                   second = args[0];
                }
                else
                {
                    first = args[0];
                    second = args[1];
                }
                int carry = 0;
                int j=0;
                StringBuilder tempSum = new StringBuilder();
                for(j=0; j<first.length(); j++)
                {
                   int sum = first.charAt(j)-'0' + second.charAt(j)-'0' + carry;
                   if(sum >=10)
                   {
                       carry = sum/10;
                       tempSum.append(sum%10);
                   }
                   else
                   {
                       carry = 0;
                       tempSum.append(sum);
                   }
                }
                for(int k=j; k<second.length();k++)
                {
                    if(carry > 0)
                    {
                        int sum = second.charAt(j)-'0' + carry;
                        carry = sum%10;
                        tempSum.append(sum/10);
                    }
                    else
                    {
                        tempSum.append(second.charAt(k));
                    }
                }
                if(carry>0)
                    tempSum.append(carry);
                int l = 0;
                for(l=0 ; l<tempSum.length();l++)
                {
                    if(tempSum.charAt(l) == '0')
                        continue;
                    else
                        break;
                }
                out.append(tempSum.substring(l)).append("\n");

            }

            System.out.println();
            System.out.print(out);
        }
        catch(Exception exc)
        {

        }
    }
}
