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
                args[0] = stripZeroFromEnds(args[0]);
                args[1] = stripZeroFromEnds(args[1]);

                String first = "";
                String second = "";
               // System.out.println("args[0]  is :"+args[0] );
                //System.out.println("args[1]  is :"+args[1] );
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
               // System.out.println("first is : "+first+" and second is: "+second);
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
                    int sum = second.charAt(k)-'0' + carry;

                    if(sum >= 10)
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
                if(l == tempSum.length() && l >0)
                    l--;

                out.append(tempSum.substring(l)).append("\n");

            }

            System.out.println();
            System.out.print(out);
        }
        catch(Exception exc)
        {
          exc.printStackTrace();
        }
    }

    private static String stripZeroFromEnds(String arg) {
        int index = 0;

        for(index=0; index<arg.length(); index++)
        {
            if(arg.charAt(index) != '0')
                break;
        }
        if(index>arg.length()-1)
        {
            index=arg.length()-1;
        }
        arg = arg.substring(index);

        for(index=arg.length()-1; index>=0; index--)
        {
            if(arg.charAt(index) != '0')
                break;
        }
        if(index<0)
        {
            index=0;
        }
        arg = arg.substring(0, index+1);


        return arg;
    }
}
