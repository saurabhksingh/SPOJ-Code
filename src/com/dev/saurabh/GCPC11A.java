package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;

/**
 * Copyright (c)
 * User: sausingh
 * Date: 20/05/2013
 * Time: 1:12 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class GCPC11A
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
                BigInteger n = new BigInteger(in[0]);
                BigInteger k = new BigInteger(in[1]);
                BigInteger factor = highestPrimeFactorForK(k);
                int power = 0;
                while(n.compareTo(factor) >= 0)
                {
                    BigInteger currentNum = new BigInteger(n.toString());

                    if(currentNum.mod(factor).equals(BigInteger.ZERO))
                    {
                        currentNum = currentNum.divide(factor);
                        power++;
                        n = n.subtract(factor);
                        while(currentNum.mod(factor).equals(BigInteger.ZERO))
                        {
                            currentNum = currentNum.divide(factor);
                            power++;
                        }
                    }
                    else
                    {
                        n = n.subtract(n.mod(factor));
                    }
                }

                System.out.println(power);
            }
        }
        catch(Exception exc)
        {

        }
    }

    private static BigInteger highestPrimeFactorForK(BigInteger k)
    {
        if(k.toString().length() < 2 || k.toString().equals("10"))
        {
            int num = k.intValue();
            int [] primes = {2,3,5,7};
            for(int i=primes.length-1; i>=0; i--)
            {
                if(num%primes[i] == 0) return new BigInteger(primes[i]+"");
            }
        }

        BigInteger bi = new BigInteger(k.toString());
        BigInteger div= new BigInteger("3");
        BigInteger two = new BigInteger("2");
        if(bi.mod(two).compareTo(BigInteger.ZERO) == 0)
        {
            while(bi.mod(two).compareTo(BigInteger.ZERO) == 0)
            {
                bi=bi.divide(two);
            }

            if(bi.compareTo(BigInteger.ONE) == 0)
            {
                return two;
            }
        }
        while(bi.compareTo(BigInteger.ONE)!=0)
        {

            while(bi.mod(div).compareTo(BigInteger.ZERO) == 0)
            {
                bi=bi.divide(div);
            }
            div = div.add(two);
        }

        return div;
    }
}
