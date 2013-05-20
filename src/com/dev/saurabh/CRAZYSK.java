package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Copyright (c)
 * User: sausingh
 * Date: 19/05/2013
 * Time: 8:06 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class CRAZYSK
{
    public static void main(String [] args)
    {

        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            int testCases = Integer.parseInt(consoleReader.readLine().trim());

            for(int i=0; i<testCases; i++)
            {
                String [] in = consoleReader.readLine().trim().split(" ");
                BigInteger itemsBought = new BigInteger(in[0]);
                BigInteger cards = new BigInteger(in[0]);
                BigInteger exchangeFactor = new BigInteger(in[1]);

                while(cards.compareTo(exchangeFactor) >=0)
                {
                    itemsBought = itemsBought.add(cards.divide(exchangeFactor));
                    cards = (cards.divide(exchangeFactor)).add(cards.mod(exchangeFactor));
                }

                System.out.println(itemsBought.toString());
            }
        }
        catch (Exception exc)
        {
            // exc.printStackTrace();
        }
    }
}
