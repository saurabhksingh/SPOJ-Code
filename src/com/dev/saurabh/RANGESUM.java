package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Copyright (c)
 * User: sausingh
 * Date: 19/05/2013
 * Time: 6:12 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class RANGESUM
{
    public static void main(String [] args)
    {

        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(consoleReader.readLine().trim());
            ArrayList <Long> array = new ArrayList<Long>(2*N);
            String [] nums = consoleReader.readLine().trim().split(" ");

            array.add(Long.parseLong(nums[nums.length-1]));
            for(int i=nums.length-2; i>=0; i--)
            {
                array.add(Long.parseLong(nums[i])+array.get(array.size()-1));
            }

            int numberOfOperations = Integer.parseInt(consoleReader.readLine().trim());

            for(int j=0; j<numberOfOperations; j++)
            {
                String operation = consoleReader.readLine().trim();
                if(operation.startsWith("1"))
                {
                    String [] in = operation.split(" ");
                    int l = array.size()-Integer.parseInt(in[1]);
                    int r = array.size()-Integer.parseInt(in[2]);
                    if(r<=0)
                    {
                        System.out.println(array.get(l));
                    }
                    else if((l<array.size() && r>0) && (r<=l))
                    {
                        long sum = (array.get(l) - array.get(r-1));
                        System.out.println(sum);
                    }
                }
                else
                {
                    String [] in = operation.split(" ");
                    array.add((Long.parseLong(in[1]) + array.get(array.size()-1)));
                }
            }
        }
        catch (Exception exc)
        {
           exc.printStackTrace();
        }
    }
}
