package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 3/27/14.
 */
public class MAJOR {
    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int testCases = Integer.parseInt(consoleReader.readLine());

            for(int i=0; i<testCases; i++){
                int count = Integer.valueOf(consoleReader.readLine().trim());
                String line = "";
                if(count > 0){
                    line = consoleReader.readLine().trim();
                }
                if(count <= 1){
                    System.out.println("NO");
                }
                else{
                    int [] nums = parseNumbers(line);
                    int majorityIndex  = getMajorityIndex(nums);
                    if(majorityIndex == -1){
                        System.out.println("NO");
                    }
                    else{
                        System.out.println("YES "+nums[majorityIndex]);
                    }
                }
            }

        }
        catch (Exception exc){

        }
    }

    private static int getMajorityIndex(int[] nums) {
        int idx = 0;


        int count  = 1;

        for(int i=1; i<nums.length; i++){
            if(nums[i] == nums[idx]){
                count++;
            }
            else{
                count--;
            }

            if(count == 0){
                idx = i;
                count = 1;
            }
        }

        count = 0;
        int majority = nums[idx];
        for(int num : nums){
            if(num == majority){
                count++;
            }
        }

        if(count > (nums.length >> 1))
        {
            return idx;
        }

        return -1;
    }

    private static int[] parseNumbers(String line) {
        String [] nums = line.split(" ");
        int [] out = new int[nums.length];
        int count = 0;
        for(String num : nums){
            out[count++] = Integer.valueOf(num.trim());
        }

        return out;
    }
}