package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by ssaurab on 1/12/15.
 */
public class Acode {

    public static void main(String [] args) throws Exception
    {
        BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
        String input = consoleReader.readLine();

        while(!"0".equals(input)){

            char [] inChars = input.toCharArray();

            int [] dp = new int[inChars.length];
            dp[0] = 1;
            for(int i=1; i<inChars.length; i++)
            {
                if(inChars[i] > '0')
                {
                    dp[i] = dp[i-1];
                }
                int combVal = Integer.parseInt(input.substring(i-1, i+1));

                if(combVal > 9 && combVal <= 26)
                {
                    dp[i] += dp[i-2 < 0?0:i-2];
                }
            }

            System.out.println(dp[inChars.length-1]);

            input = consoleReader.readLine();
        }


    }
}
