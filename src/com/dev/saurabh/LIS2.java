package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 9/28/12
 * Time: 9:00 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class LIS2 {

    private static class Point
    {
        private int x,y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

       @Override
       public boolean equals(Object other)
       {
           Point otherPoint = (Point)other;
           return (this.x == otherPoint.x && this.y == otherPoint.y);
       }
    }
    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int pairCount = Integer.parseInt(consoleReader.readLine());
            if(pairCount <= 0)
            {
                System.out.println(0);
                return;
            }
            Point [] sortedInput = new Point[pairCount];
            Point [] input = new Point[pairCount];
            String [] pair = null;

            for(int i=0; i<pairCount; i++)
            {
                pair = consoleReader.readLine().split(" ");
                int x = Integer.parseInt(pair[0]);
                int y = Integer.parseInt(pair[1]);
                Point currentPoint = new Point(x,y);
                input [i] = currentPoint;
                sortedInput [i] = currentPoint;
            }

            Arrays.sort(sortedInput, new Comparator<Point>() {
                @Override
                public int compare(Point point1, Point point2) {

                    if(point1.x < point2.x && point1.y < point2.y)
                    {
                        return -1;
                    }
                    else if(point1.x == point2.x && point1.y == point2.y)
                    {
                        return 0;
                    }

                    return 1;
                }
            });
            System.out.println(lcs(input, sortedInput));
        }
        catch(Exception exc)
        {

        }
    }

    private static int lcs(Point[] input, Point[] sortedInput) {

        int [][] L = new int [input.length+1][sortedInput.length + 1];

        for (int i=0; i<=input.length; i++)
        {
            for (int j=0; j<=sortedInput.length; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;

                else if (input[i-1].equals(sortedInput[j-1]))
                    L[i][j] = L[i-1][j-1] + 1;

                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }

        return L[input.length][sortedInput.length];

    }
}
