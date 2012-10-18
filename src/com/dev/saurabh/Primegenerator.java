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
public class PrimeGenerator {
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

                    int [][] ranges = new int [inputRangeList.size()][2];
                    int count = 0;
                    for(String range  : inputRangeList){
                        String [] rangeEnds = range.split(" ");

                        int start = Integer.parseInt(rangeEnds[0]);
                        int end = Integer.parseInt(rangeEnds[1]);

                        ranges[count][0] = start;
                        ranges[count][1] = end;
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
                       StringBuilder out = new StringBuilder();
                       if(start < 3)
                       {
                           out.append(2).append("\n");
                           start = 3;
                       }
                       if(start%2 == 0)start++;
                       if(end%2  == 0)end--;

                       //System.out.println(""+start+" "+end);
                       int totalRange = (int)(end-start)/2 + 1;
                       BitSet nonPrimeNumbers = new BitSet(totalRange);
                       int upTo  = (int)Math.sqrt(end);
                       BitSet factorsTried = new BitSet((upTo-3)/2 +1);
                       for (int num = 3; num <=upTo ; num=num+2)
                       {
                           if(factorsTried.get((num-3)/2))
                           {
                               continue;
                           }
                           long tempStart = start;
                           int currentSeiveFactor = num;
                           if(tempStart <= currentSeiveFactor)
                           {
                               tempStart = currentSeiveFactor+1;
                           }
                           while(tempStart%currentSeiveFactor  != 0)
                           {
                               tempStart++;
                           }
                           long currentNumber = tempStart;
                           int skipInterval = currentSeiveFactor;

                           if(tempStart%2 == 0)
                           {
                               currentNumber +=  skipInterval;
                           }
                           skipInterval = currentSeiveFactor*2;
                          // System.out.println("**");

                           for(int j=num; j<=upTo; j=j+num*2)
                               factorsTried.set((j-3)/2);

                           while(currentNumber <= end)
                           {
                               //System.out.println("start is : "+start+" skipInterval: "+skipInterval+" "+currentNumber);
                               if(((currentNumber-start)/2)>0)
                               {
                                   //System.out.println("setting : "+(int)((currentNumber-start)/2));
                                   nonPrimeNumbers.set((int) ((currentNumber - start) / 2), true);
                               }
                               currentNumber += skipInterval;
                               //System.out.println("currentNumber after adding "+skipInterval+" is:"+currentNumber+" ,end is :"+end+" , is current number less tha end : "+(currentNumber<=end));
                               if(currentNumber > end)
                                   break;

                           }
                       }

                       for(int k=0 ; k<totalRange; k++)
                       {
                           if(!nonPrimeNumbers.get(k))
                               out.append(k*2+start).append("\n");
                       }

                       System.out.print(out);
                   }

                  // System.out.println(System.currentTimeMillis() - startTime);
                }
            }
            catch(Exception exc){
                exc.printStackTrace();
            }
        }
    }