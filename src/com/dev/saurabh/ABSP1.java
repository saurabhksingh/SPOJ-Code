package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 5/24/14.
 */
public class ABSP1 {
    public static void main(String [] args){

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int totalNumberOfCases = Integer.parseInt(br.readLine());
            for(int i=0; i<totalNumberOfCases; i++){
                int n = Integer.parseInt(br.readLine());
                String [] numbers = br.readLine().split(" ");

                long [] diffs = new long [numbers.length-1];
                long [] cumulativeDiffs = new long [numbers.length-1];

                long sum = 0;

                long prev = 0;
                for(int j=0; j<diffs.length; j++){
                    diffs[j] = (Long.parseLong(numbers[j+1]) - Long.parseLong(numbers[j]) );
                    cumulativeDiffs[j] = diffs[j] + prev;
                    sum += cumulativeDiffs[j];
                    prev = diffs[j];
                }

                long tempSum = sum;

                for(int k=diffs.length; k>1; k--){
                    cumulativeDiffs[k-1] = cumulativeDiffs[k-1] - diffs[k-2];
                    sum = sum-diffs[diffs.length-k]*k;
                    tempSum += sum;
                }

                System.out.println(tempSum);
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}