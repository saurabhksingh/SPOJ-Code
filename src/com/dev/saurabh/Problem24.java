package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/18/12
 * Time: 2:46 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem24 {
    /**
     * Small factorials
     */

    public static void main(String [] args)
    {
        try
        {
            BigInteger[] factorialCache = new BigInteger[100];
            factorialCache[0] = BigInteger.ONE;
            factorialCache[1] = new BigInteger(""+2);
            StringBuilder out = new StringBuilder();
            //.append(1).append("\n");
            //out.append(1).append("\n");
            for(int k=2 ; k<100; k++)
            {
              factorialCache[k] = factorialCache[k-1].multiply(new BigInteger(""+(k+1)));
              //out.append(factorialCache[k]).append("\n");
            }

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int numOfTestCases = Integer.parseInt(consoleReader.readLine());
            //int [] input = new int[numOfTestCases];
            for(int i=0 ;i<numOfTestCases; i++)
            {
                int input = Integer.parseInt(consoleReader.readLine());
                out.append(factorialCache[input-1]).append("\n");
            }

            System.out.println();

            System.out.print(out);
        }
        catch(Exception exc)
        {

        }
    }

}
