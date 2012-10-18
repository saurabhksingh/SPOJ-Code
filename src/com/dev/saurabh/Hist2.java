package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 9/26/12
 * Time: 10:34 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Hist2 {

    private static int [] input = null;
    private static int [][] maxSum = null;
    private static int [][] permCount = new int[1<<16][16];
    private static boolean [][] isVisited = null;
    private static int ret = 0;

    public static void main(String [] args) throws Exception
    {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            int testCases = Integer.parseInt(consoleReader.readLine());
            if(testCases == 0)
            {
                break;
            }
            else
            {
                input = new int[testCases];
                String [] bars = consoleReader.readLine().split(" ");
                for(int j=0; j<testCases; j++)
                {
                    input[j] = Integer.parseInt(bars[j]);
                }
                maxSum  = new int[1<<16][16];
                isVisited = new boolean[1<<16][16];
                int maxVal = 0;
                int val = 0;
                int count = 0;
                for(int i = 0; i < testCases; i++)
                {
                    val = getParameter(1 << i, i, testCases) + input[i];
                    if(val > maxVal)
                    {
                        maxVal = val;
                        count = 0;
                    }
                    if(val==maxVal) count += permCount[1<<i][i];
                }
                maxVal += (testCases << 1);

                System.out.println(maxVal+ " "+ count);
            }
        }
    }

    private static int getParameter(int mask, int index, int testCases) {
        if(mask==(1<<testCases)-1)
        {
            permCount[mask][index] = 1;
            return input[index];
        }
        ret = maxSum[mask][index];
        if(isVisited[mask][index])
        {
            return ret;
        }

        isVisited[mask][index] = true;

        for(int i = 0, val; i < testCases; i++)
        {
            if((mask & (1 << i)) != 0)
            {
                val = getParameter(mask | (1 << i), i, testCases) + Math.abs(input[i]-input[index]);
                if(val > ret)
                {
                    ret = val;
                    permCount[mask][index] = 0;
                }
                if(val==ret) permCount[mask][index] += permCount[mask | (1 << i)][i];
            }
        }
        return ret;
    }
}
