package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 5/24/14.
 */
public class PUCMM334 {
    public static void main(String [] args){

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            String [] input = br.readLine().split(" ");
            System.out.println(parseAndValidateInput(input));
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    private static Integer parseAndValidateInput(String[] input) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int [] positionsAllotted = new int[input.length];

        int [] temp = new int[input.length];

        for(int i=0; i<input.length; i++){
            int result = Integer.parseInt(input[i]);
            temp[i] = result;
            if(max < result){
                max = result;
            }

            if(min > result){
                min = result;
            }

            if(max >= input.length) return -1;

        }

        int totalCount = 0;

        for(int i=0; i<input.length; i++){

            if(temp[i] == max){
                positionsAllotted[i] = 1;
                totalCount++;
            }
            else if(temp[i] == min){
                positionsAllotted[i] = 0;
            }
            else{
                return -1;
            }
        }

        if(input.length > 2){
            for(int i=0; i<input.length; i++){

                if(positionsAllotted[i] == 1 && temp[i] != totalCount - 1){
                    return -1;
                }
                if(positionsAllotted[i] == 0 && temp[i] != totalCount){
                    return -1;
                }
            }
        }




        return totalCount;
    }
}