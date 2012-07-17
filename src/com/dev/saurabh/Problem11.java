package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/18/12
 * Time: 2:30 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem11 {
    /**
     * Number of zeroes in factorial
     */
    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int numOfTestCases = Integer.parseInt(consoleReader.readLine());
            int [] input = new int[numOfTestCases];
            for(int i=0; i<numOfTestCases; i++)
            {
                input[i] = Integer.parseInt(consoleReader.readLine());
            }

            System.out.println();

            for(int num : input)
            {
                int sum = 0;
                int count = 1;
                int currentCount = 0;
                while((currentCount = (int)(num/5)) != 0)
                {
                    sum += currentCount;
                    num = currentCount;
                }

                System.out.println(sum);
            }
        }
        catch(Exception exc)
        {

        }
    }
}
