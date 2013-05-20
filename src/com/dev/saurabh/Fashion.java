package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Fashion {

    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            int testCases = Integer.parseInt(consoleReader.readLine());
            for(int i=0; i<testCases; i++)
            {
                int pairs = Integer.parseInt(consoleReader.readLine());
                String [] men = consoleReader.readLine().split(" ");
                String [] women = consoleReader.readLine().split(" ");

                int [] menRatings = new int [pairs];
                int [] womenRatings = new int [pairs];
                for(int j=0; j<pairs; j++)
                {
                    menRatings[j] = Integer.parseInt(men[j]);
                    womenRatings[j] = Integer.parseInt(women[j]);
                }

                Arrays.sort(menRatings);
                Arrays.sort(womenRatings);

                int sum = 0;

                for(int k=pairs-1; k>=0; k--)
                {
                    sum += womenRatings[k]*menRatings[k];
                }

                System.out.println(sum);
            }

        }
        catch(Exception exc)
        {

        }
    }
}
