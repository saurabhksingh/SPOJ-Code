package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaurab on 3/27/14.
 */
public class GSSQUNCE {

    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int testCases  = Integer.valueOf(consoleReader.readLine().trim());

            for(int i=0; i<testCases; i++){
                int countOfNumbers = Integer.valueOf(consoleReader.readLine().trim());
                boolean canBeSplit = countOfNumbers>1;
                String inputLine = consoleReader.readLine().trim();

                if(canBeSplit){
                    Map<String, Integer> map = new HashMap<String, Integer>(countOfNumbers);
                    String [] nums = inputLine.split(" ");
                    for(String num : nums){
                        num = num.trim();
                        Integer count = map.get(num);
                        if(count == null){
                            map.put(num, 1);
                        }
                        else{
                            if(count == 2){
                                canBeSplit = false;
                                break;
                            }

                            map.put(num, count+1);
                        }
                    }
                }

                if(canBeSplit){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            }


        }
        catch(Exception exc){

        }
    }
}
