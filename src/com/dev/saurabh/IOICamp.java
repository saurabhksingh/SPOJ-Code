package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 9/27/12
 * Time: 10:45 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class IOICamp {

    public static void main(String [] args) throws Exception
    {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        int rowCount = Integer.parseInt(consoleReader.readLine());

        double [][] maxMin  = new double [2][8];
        for(int i=0; i<8; i++)
        {
            maxMin[0][i] = Double.MAX_VALUE;
            maxMin[1][i] = -1*Double.MIN_VALUE;
        }

        for(int i=0; i<rowCount; i++)
        {
            String [] in = consoleReader.readLine().split(" ");
            double a = Double.parseDouble(in[0]); double b = Double.parseDouble(in[1]);
            double c = Double.parseDouble(in[2]); double d = Double.parseDouble(in[3]);

            double temp = a + b + c + d;
            if (temp < maxMin[0][0]) maxMin[0][0] = temp;
            if ( temp > maxMin[1][0] ) maxMin[1][0] = temp;

            temp = a + b + c - d;
            if ( temp < maxMin[0][1] ) maxMin[0][1] = temp;
            if ( temp > maxMin[1][1] ) maxMin[1][1] = temp;

            temp = a + b - c + d;
            if ( temp < maxMin[0][2] ) maxMin[0][2] = temp;
            if ( temp > maxMin[1][2] ) maxMin[1][2] = temp;

            temp = a + b - c - d;
            if ( temp < maxMin[0][3] ) maxMin[0][3] = temp;
            if ( temp > maxMin[1][3] ) maxMin[1][3] = temp;

            temp =  a - b + c + d;
            if ( temp < maxMin[0][4] ) maxMin[0][4] = temp;
            if ( temp > maxMin[1][4] ) maxMin[1][4] = temp;

            temp =  a - b + c - d;
            if ( temp < maxMin[0][5] ) maxMin[0][5] = temp;
            if ( temp > maxMin[1][5] ) maxMin[1][5] = temp;

            temp =  a - b - c + d;
            if (temp < maxMin[0][6] ) maxMin[0][6] = temp;
            if ( temp > maxMin[1][6] ) maxMin[1][6] = temp;

            temp =  a - b - c - d;
            if ( temp < maxMin[0][7] ) maxMin[0][7] = temp;
            if ( temp > maxMin[1][7] ) maxMin[1][7] = temp;
        }
        double maxSum = -1*Double.MIN_VALUE;
        for(int i=0; i<=7; i++)
        {
            double sum =  maxMin[1][i]-maxMin[0][i];
            if(maxSum < sum)
            {
               maxSum = sum;
            }
        }

        DecimalFormat df = new DecimalFormat(".000");
        df.setRoundingMode(RoundingMode.DOWN); // Note this extra step
        System.out.print(df.format(maxSum));
    }

}
