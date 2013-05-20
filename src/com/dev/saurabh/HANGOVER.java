package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: sausingh
 * Date: 19/05/2013
 * Time: 1:49 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class HANGOVER
{
    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String line = consoleReader.readLine().trim();
            while(!"0.00".equals(line))
            {
                double currentLength;
                int cardCount = 0;
                double desiredLength = Double.valueOf(line);
                while(desiredLength > 0)
                {
                    cardCount++;
                    currentLength = (1.0/(cardCount+1));
                    desiredLength -= currentLength;
                }

                System.out.println(cardCount + " card(s)");
                line = consoleReader.readLine().trim();
            }
        }
        catch(Exception exc)
        {

        }
    }
}
