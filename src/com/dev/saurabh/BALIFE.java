package com.dev.saurabh;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 9/25/12
 * Time: 11:26 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */

import java.io.*;


public class BALIFE {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean blockEnded = true;

        int[] loads = null;

        int j = 0;
        StringBuilder output = new StringBuilder();
        int size = 0;
        while(true) {

            String input = br.readLine();
            if("-1".equals(input)){
                break;
            } else if ("".equals(input)) {
                blockEnded = true;
                continue;
            } else if(blockEnded){
                size = Integer.parseInt(input);
                loads = new int[size];
                blockEnded = false;
            } else {
                String[] temp = input.split(" ");
                for(int i = 0; i < temp.length; i++) {

                    loads[j] = Integer.parseInt(temp[i]);
                    j++;
                }
                if(j == size) {
                    int sum = getSum(loads);
                    if(sum % size != 0) {
                        output.append("-1\n");

                    } else {
                        int taskPerMach = sum/size;
                        int rounds = getSteps(loads, taskPerMach);
                        output.append(rounds + "\n");
                        loads = null;
                    }
                    size = 0;
                    j = 0;
                }

            }

        }



        System.out.println(output.toString());

    }



    private static int getSteps(int[] load, int targetLoadForEachProcessor) {

        int maxStep = 0;
        for(int j=0; j<load.length-1; j++)
        {
            int currentDiff = load[j]-targetLoadForEachProcessor;

//this much amount has to be off-loaded to the next processor

//negative value of currentDiff will mean that this much amount

//will be taken by the next processor. So the bottom line is if the

//current processor is overloaded then pass the excess to next processor

//This logic should work as the first processor will never have any other option :P

//If the current processor is under-loaded then it will take the missing amount of

//work to balance from the next processor. We are iterating till second last

//element as the either the negative (if it has excess) or positive(if under-loaded)

//difference will be added to it and also it does not have any choice to pass the work to

//worker processor.

            load[j+1] = load[j+1] + currentDiff;
            currentDiff = Math.abs(currentDiff);
            maxStep = Math.max(currentDiff, maxStep);
        }

        return maxStep;
    }

    private static int getSum(int[] jobs) {

        int sum = 0;

        for(int i = 0 ; i < jobs.length; i++) {

            sum += jobs[i];

        }

        return sum;


    }

}