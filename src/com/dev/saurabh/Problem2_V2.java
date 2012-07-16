package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/12/12
 * Time: 12:04 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem2_V2 {
    /**
     *
     * Peter wants to generate some prime numbers for his cryptosystem. Help him! Your task is to generate
     all prime numbers between two given numbers!
     */

    public static void main(String [] args){

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try{

            int[] primeNumbers = new int[4000];
            int primeCount = 0;

            primeNumbers[primeCount++] = 2;
            int numberToLookUpTo = 32000;
            for (int num = 3; num <= numberToLookUpTo; num = num + 2) {
                boolean wasPrime = true;
                int numberToCheckTill = (int) Math.sqrt(num);
                for (int i = 3; i <= numberToCheckTill; i = i + 2) {

                    if ((num % i) == 0) {
                        wasPrime = false;
                        //number is not prime
                        break;
                    }
                }
                if(wasPrime)
                {
                    primeNumbers[primeCount++] = num;
                }
            }
                int numOfTestCases = Integer.parseInt(consoleReader.readLine());
            if(numOfTestCases > 0 && numOfTestCases < 11){
                List<String> inputRangeList = new ArrayList<String>();
                for(int i=0; i<numOfTestCases; i++){
                    inputRangeList.add(consoleReader.readLine());
                }

                int [][] ranges = new int [inputRangeList.size()][2];
                int count = 0;
                int largestEnd = Integer.MIN_VALUE;
                for(String range  : inputRangeList){
                    String [] rangeEnds = range.split(" ");

                    int start = Integer.parseInt(rangeEnds[0]);
                    int end = Integer.parseInt(rangeEnds[1]);

                    ranges[count][0] = start;
                    ranges[count][1] = end;
                    if(end > largestEnd)largestEnd = end;
                    count++;
                }

                //[0] is 3, [1] is 5, [2] is 7.....(index+1)*2 + 1
                //for 3 at [0]odd multiples are 9, 15, 21, 27...index will be 3, 6, 9,.....
                //for 5 at [1] odd multiples are 15, 25, 35, 45...index will be 6, 11, 16.....
                //doing Seive
                //long startTime = System.currentTimeMillis();
                for(int i=0; i<inputRangeList.size(); i++)
                {

                    System.out.println();

                    int start = ranges[i][0];
                    int end = ranges[i][1];

                    if (start < 2)
                    {
                       start = 2;
                    }

                    boolean[] isPrime = new boolean[100001];
                    for (int j = 0; j < 100001; j++) {
                        isPrime[j] = true;
                    }

                    for (int j = 0; j < primeCount; j++)
                    {
                        int prime = primeNumbers[j];
                        int localStart;

                        if (prime >= start) localStart = prime*2;
                        else localStart = start + ((prime - start % prime)%prime);

                        for (int k = localStart; k <= end; k += prime) {
                            isPrime[k - start] = false;
                        }
                    }
                    StringBuilder out = new StringBuilder();
                    for (int j = start; j <= end; j++) {
                        if (isPrime[j-start]) out.append(j).append("\n");
                    }
                    System.out.print(out);
//
//                    for(int k=0 ; k<totalRange; k++)
//                    {
//                        if(!nonPrimeNumbers.get(k))
//                            out.append(k*2+start).append("\n");
//                    }
//
//                    System.out.print(out);
                }

                // System.out.println(System.currentTimeMillis() - startTime);
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
}