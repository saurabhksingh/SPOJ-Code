package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/12/12
 * Time: 12:07 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem5 {
    /**
     * A positive integer is called a palindrome if its representation in the decimal system is the same when
     read from left to right and from right to left. For a given positive integer K of not more than 1000000
     digits, write the value of the smallest palindrome larger than K to output. Numbers are always
     displayed without leading zeros.
     */

    public static void main(String [] args){

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int totalNumberOfCases = Integer.parseInt(br.readLine());
            StringBuilder out = new StringBuilder();
            for(int i=0; i<totalNumberOfCases; i++){

                String input = br.readLine();
                out.append(getNearestPalindrome(input)).append("\n");
            }

            System.out.println(out.toString());
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    private static String getNearestPalindrome(String input) {
        String result =  "";
        int length = input.length();
        char [] inCharArray = input.toCharArray();
        int counter = 0;
        for(int i = 0; i<length; i++)
        {
            if(inCharArray[i] == '0')
            {
                counter++;
            }
            else
            {
                break;
            }

        }
        if(counter == length) input="0";
        else input = input.substring(counter);
        inCharArray  = input.toCharArray();
        length = inCharArray.length;

        if(input.length() < 2){
            char ch = input.charAt(0);
            if(ch == '9')
            {
                result = new String("11");
            }
            else
            {
                ch++;
                result = new String(""+ ch);
            }
        }
        else
        {
            int leftCursor = 0;
            int rightCursor = length-1;
            int mid = length/2;
            boolean isNumberIncreased = false;

            while(leftCursor < rightCursor)
            {
                char first = input.charAt(leftCursor);
                char second = input.charAt(rightCursor);
                if(first > second)
                {
                    isNumberIncreased = true;
                }
                if(second > first)
                {
                    isNumberIncreased = false;
                }
                inCharArray[rightCursor] = inCharArray[leftCursor];

                leftCursor++;
                rightCursor--;
            }
            if(!isNumberIncreased)
            {

                if(inCharArray[mid] < '9')
                {
                    inCharArray[mid]++;
                    if(length % 2 == 0)
                    {
                        inCharArray[mid-1] = inCharArray[mid];
                    }

                }
                else
                {
                    int tempMid = mid;

                    while(tempMid < length && inCharArray[tempMid] == '9')
                    {
                        inCharArray[tempMid] = '0';
                        inCharArray[length-tempMid-1] = inCharArray[tempMid];
                        tempMid++;
                    }
                    if(tempMid < length)
                    {
                        inCharArray[tempMid]++;
                        inCharArray[length-tempMid-1] = inCharArray[tempMid];
                        inCharArray[mid] = '0';
                        if(length % 2 == 0)
                        {
                            inCharArray[mid-1] = '0';
                        }
                    }
                    else
                    {
                        StringBuilder out = new StringBuilder();
                        out.append('1');
                        int iterateUpTo = length-1;

                        for(int i =0; i<iterateUpTo; i++)
                        {
                            out.append('0');
                        }
                        result = out.append('1').toString();
                        return  result;
                    }
                }
            }
            // System.out.println(isNumberIncreased);
            result = new String(inCharArray);
        }
        return result;
    }

    private static boolean isPalindrome(String input) {
        int length = input.length();
        boolean isPalindrome = true;
        char [] inCharArray = input.toCharArray();
        int low = 0;
        int high = length-1;
        while(low <= high)
        {
            if(inCharArray[low] != inCharArray[high])
            {
                isPalindrome = false;
                break;
            }
            low++;
            high--;
        }

        return isPalindrome;
    }
}
