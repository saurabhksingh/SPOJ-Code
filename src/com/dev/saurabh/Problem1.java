package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/12/12
 * Time: 12:03 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem1 {
    /**
     * Your program is to use the brute-force approach in order to find the Answer to Life, the Universe, and
     Everything. More precisely... rewrite small numbers from input to output. Stop processing input after
     reading in the number 42. All numbers at input are integers of one or two digits.
     */
    private static final String regExpression = "([0-9]+).*";
    private static final Pattern pattern =    Pattern.compile(regExpression);

    public static void main(String [] args){

        StringBuilder out = new StringBuilder();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while(true){

            try{
                input = buffReader.readLine();

                int num = Integer.parseInt(input);
                if(num == 42) break;
                out.append(num);
            }
            catch(Exception exc){
                exc.printStackTrace();
            }

            out.append("\n");
        }

        System.out.println(out);
    }
}

