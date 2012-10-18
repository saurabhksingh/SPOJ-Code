package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 9/28/12
 * Time: 9:00 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class LIS2 {

    public static void main(String [] args) throws Exception
    {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int pairCount = Integer.parseInt(consoleReader.readLine());

        int [][] tailTable = new int[2][pairCount];
        int [][] input = new int[2][pairCount];
        String [] pair = consoleReader.readLine().split(" ");
        tailTable[0][0] = Integer.parseInt(pair[0]);
        tailTable[1][0] = Integer.parseInt(pair[1]);
        input[0][0] = tailTable[0][0];
        input[1][0] = tailTable[1][0];
        int len = 1;

        for(int i=1; i<pairCount; i++)
        {
            pair = consoleReader.readLine().split(" ");
            int x = Integer.parseInt(pair[0]);
            int y = Integer.parseInt(pair[1]);
            input[0][i] = x;input[1][i] = y;

            if( input[0][i] <= tailTable[0][0] && input[1][i] <= tailTable[1][0] )
            {
                tailTable[0][0] = input[0][i];
                tailTable[1][0] = input[1][i];
            }
            else if( input[0][i] >= tailTable[0][len-1] &&  input[1][i] >= tailTable[1][len-1])
            {
                tailTable[0][len] = input[0][i];
                tailTable[1][len] = input[1][i];
                len++;
            }
            else
            {
                int upperIndex = getUpperIndex(tailTable, -1, len-1, input[0][i], input[1][i]);

                tailTable[0][upperIndex] = input[0][upperIndex]; tailTable[1][upperIndex] = input[1][upperIndex];
            }
        }

        System.out.println(len);
    }

    private static int getUpperIndex(int[][] tailTable, int l, int r, int x, int y) {
        int m;

        while( r - l > 1 ) {
            m = l + (r - l)/2;
            if(tailTable[0][m] >= x && tailTable[1][m] >= y)
            {
               r = m;
            }
            else
            {
                l = m;
            }
        }

        return r;
    }
}
