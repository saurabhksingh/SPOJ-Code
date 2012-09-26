package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 9/26/12
 * Time: 2:05 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Encodin {

    public static void main(String [] args)  throws Exception
    {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while((input = consoleReader.readLine()) != "" && input != null)
        {
            if(input.length() == 0)
            {
                System.out.println();
                continue;
            }

            StringBuilder out = new StringBuilder();
            if(input.length() == 1)
            {
              if("1".equals(input))
              {
                  out.append("11");
              }
              else
              {
                  out.append(1).append(input).append(1);
              }

            }
            else
            {
                for(int i=0; i<input.length();)
                {
                    if(i==input.length()-1)
                    {
                        //if(input.charAt(i)==input.charAt(i-1))
                        {
                            if('1'==input.charAt(i))
                            {
                                out.append(11);
                            }
                            else
                            {
                                out.append(1).append((char)input.charAt(i)).append(1);
                            }
                        }

                        i++;
                    }
                    else if(i<input.length()-1 && input.charAt(i) != input.charAt(i+1))
                    {
                        out.append(1);
                        int j=i;
                        for(; j<input.length()-1 && input.charAt(j)!=input.charAt(j+1);j++,i++)
                        {
                            if(input.charAt(j) == '1')
                            {
                                out.append(11);
                            }
                            else
                            {
                                out.append((char)input.charAt(j));
                            }
                        }
                        if((input.charAt(j) != input.charAt(j-1)) && j==input.length()-1)
                        {
                            if('1'==input.charAt(j))
                            {
                                out.append(11);
                            }
                            else
                            {
                                out.append(input.charAt(j));
                            }
                            i++;
                        }
                        out.append(1);
                    }
                    else
                    {
                        char currenrChar = input.charAt(i);
                        int count = 0;
                        for(int j=i; j<input.length() && count<9 && input.charAt(j)==currenrChar;j++,i++,count++);
                        out.append(count).append((char)currenrChar);
                    }
                }
            }
            if(out.length() > 0)
            System.out.println(out);
        }
    }
}
