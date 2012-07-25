package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/25/12
 * Time: 4:40 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */

//COMMEDIA
public class Problem45
{
    private static int [] tempArray = null;
    private static int emptyPosition;
    private static boolean hasEmptyLineBeenPrinted = false;

    private static final String SUCCESS_MSG = "Puzzle can be solved.";
    private static final String FAIL_MSG = "Puzzle is unsolvable.";

    public static void main(String [] args)
    {
        try
        {

            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int numberOfTestCases = Integer.parseInt(consoleReader.readLine());
            StringBuilder out = new StringBuilder();
            for(int i=numberOfTestCases; i>0; i--)
            {
                int x0=0;
                int y0=0;
                int z0=0;
                int m = Integer.valueOf(consoleReader.readLine());
                int [] board = new int[m*m*m];
                tempArray = new int[m*m*m];
                int count = 0;
                for(int j=m; j>0; j--)
                {
                    String [] data = consoleReader.readLine().split(" ");
                    for(String num : data)
                    {
                       int number= Integer.valueOf(num);

                       if(number == 0)
                       {
                            emptyPosition = count;
                       }

                        board[count++]=number;
                    }
                }

                if(!hasEmptyLineBeenPrinted)
                {
                    System.out.println();
                    hasEmptyLineBeenPrinted = true;
                }
                x0=emptyPosition%m;	emptyPosition/=m;
                y0=emptyPosition%m;	emptyPosition/=m;
                z0=emptyPosition;

                int m2 = m*m;

                int yz = m*((y0)+m*(z0));
                while(x0 < m-1)
                {
                  int index = x0+yz;
                  board[index] = board[index+1];
                  x0++;
                }

                while(y0 < m-1)
                {
                    int index1 =  x0+m*(y0+m*z0);
                    int index2 = index1 + m;
                    board[index1] = board[index2];
                    y0++;
                }

                while(z0 < m-1)
                {
                    int index1 =  x0+m*(y0+m*z0);
                    int index2 = index1 + m2;
                    board[index1] = board[index2];
                    z0++;
                }
                int index = z0*m2 + y0*m +x0;
                board[index] = 0;

//                for(int j=board.length-1; j>=0; j--)
//                {
//                    System.out.println(board[j]);
//                }

                long inversionCount = getInversionCount(board, 0, board.length-2);

                /*getParity logic works here too :) */
                if(inversionCount%2 == 0/*!getParity(board)*/)
                {
                    System.out.println(SUCCESS_MSG);
                }
                else
                {
                    System.out.println(FAIL_MSG);
                }
            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private static long getInversionCount(int[] board, int left, int right)
    {
        long inversionCount = 0;

        if(right > left)
        {
            int mid = left + (right-left)/2;//optimize (left+right)/2

            inversionCount +=  getInversionCount(board, left, mid);
            inversionCount +=  getInversionCount(board, mid+1, right);
            inversionCount +=  merge(board, left, mid+1, right);
        }

        return inversionCount;
    }

    private static long merge(int[] board, int left, int mid, int right)
    {
        long inversionCount = 0;
        int i=left,j=mid,k=left;

        while ((i <= mid - 1) && (j <= right))
        {
            if (board[i] <= board[j])
            {
                tempArray[k++] = board[i++];
            }
            else
            {
                tempArray[k++] = board[j++];
                inversionCount = inversionCount + (mid - i);
            }
        }

        while (i <= mid - 1)
            tempArray[k++] = board[i++];

        while (j <= right)
            tempArray[k++] = board[j++];

        for (i=left; i <= right; i++)
            board[i] = tempArray[i];


        return inversionCount;
    }

    private static boolean getParity(int[] in) {

        boolean result = false;
        int n = 0;
        for(int j=in.length-2; j>=0; j--)
        {
            while((n=in[j]) != (j+1))
            {
               // System.out.println("n:"+n+" j:"+j);
                in[j] = in[n-1];
                in[n-1] = n;
                result = !result;
            }
        }

        return result;
    }
}
