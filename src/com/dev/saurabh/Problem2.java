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
public class Problem2 {
    /**
     *
     * Peter wants to generate some prime numbers for his cryptosystem. Help him! Your task is to generate
     all prime numbers between two given numbers!
     */
    public static void main(String [] args){

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            int numOfTestCases = Integer.parseInt(consoleReader.readLine());
            if(numOfTestCases > 0 && numOfTestCases < 11){
                List<String> inputRangeList = new ArrayList<String>();
                for(int i=0; i<numOfTestCases; i++){
                    inputRangeList.add(consoleReader.readLine());
                }

                int smallestStart = Integer.MAX_VALUE;
                int largestEnd = Integer.MIN_VALUE;
                long smallBound = 0;
                long largeBound = 0;
                int [][] ranges = new int [inputRangeList.size()][2];
                int count = 0;
                int arraySizeToProcess = 0;
                for(String range  : inputRangeList){

                    String [] rangeEnds = range.split(" ");
                    smallBound = Long.parseLong(rangeEnds[0]);
                    largeBound = Long.parseLong(rangeEnds[1]) ;
                    if(smallBound > 1000000000 || largeBound > 1000000000) continue;
                    arraySizeToProcess++;
                    int start = Integer.parseInt(rangeEnds[0]);
                    int end = Integer.parseInt(rangeEnds[1]);
                    if(start < smallestStart)
                        smallestStart = start;
                    if(end > largestEnd)
                        largestEnd = end;

                    ranges[count][0] = start;
                    ranges[count][1] = end;

                    count++;
                }
                if(smallestStart % 2 == 0) smallestStart++;

                if(largestEnd % 2 == 0) largestEnd--;


                int totalNumsToIterate = (largestEnd-1)/2+1;
                //System.out.println("totalNumsToIterate : "+totalNumsToIterate);
                BitSet nonPrimeNumbers = new BitSet(totalNumsToIterate);     //[0] is 3, [1] is 5, [2] is 7.....(index+1)*2 + 1
                //for 3 at [0]odd multiples are 9, 15, 21, 27...index will be 3, 6, 9,.....
                //for 5 at [1] odd multiples are 15, 25, 35, 45...index will be 6, 11, 16.....

                int index = 0;
                //doing Seive
                for (int num = 0; num <= ((largestEnd-1)/2+1); num++) {
                    //System.out.print("**___"+num+"___**");
                    int currentNumber = num * 2 + 3;
                    int skipInterval = (num+3) + num * 2;
                    int remainder = smallestStart % currentNumber;
                    int offset = smallestStart + remainder;
                    if(!nonPrimeNumbers.get(skipInterval)){
                        while((skipInterval + offset) < totalNumsToIterate){
                            if(!nonPrimeNumbers.get(offset + skipInterval)){
                                //System.out.print("__ "+currentIndex+"__");
                                nonPrimeNumbers.set(offset + skipInterval, true);
                            }
                            // System.out.println("skipInterval : "+skipInterval);
                            skipInterval = currentNumber+skipInterval;
                        }
                    }

                }

                //System.out.println(" **  "+nonPrimeNumbers.length);
                String [] out = new String[inputRangeList.size()];
                for(int i=0; i<arraySizeToProcess; i++){
                    StringBuilder primeNumbers = new StringBuilder();
                    int start = ranges[i][0];
                    int end = ranges[i][1];
                    if(start < 3){
                        primeNumbers.append(2).append("\n");
                        start = 3;
                    }
                    if(start%2 == 0) start++;
                    if(end%2 == 0) end--;
                    for(int k = start; k<=end; k=k+2){
                        if(!nonPrimeNumbers.get((k-3)/2)) {
                            System.out.println(k);
                        }
                    }
                    System.out.println();
                }
            }

        }
        catch(Exception exc){
            //exc.printStackTrace();
        }
    }
}
