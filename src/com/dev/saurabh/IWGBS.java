package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Copyright (c)
 * User: sausingh
 * Date: 20/05/2013
 * Time: 1:01 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class IWGBS
{
    public static void main(String [] args)
    {

        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(consoleReader.readLine().trim());


            if(N == 1)
            {
                System.out.println(2);
            }
            else if(N == 2)
            {
                System.out.println(3);
            }
            else
            {
                BigInteger f1 = new BigInteger("2");
                BigInteger f2 = new BigInteger("3");

                BigInteger result = null;
                for(int i=0; i<N-2; i++)
                {
                    result = f1.add(f2);
                    f1 = f2;
                    f2 = result;
                }

                System.out.println(result.toString());
            }
        }
        catch(Exception exc)
        {

        }
    }
}
