package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/17/12
 * Time: 5:05 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem8
{
    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int numberOfTestCases = Integer.parseInt(consoleReader.readLine());
            String [][] input = new String[numberOfTestCases][2];
            for(int i=0; i<numberOfTestCases; i++)
            {
                input[i][0] = consoleReader.readLine();
                input[i][1] = consoleReader.readLine();
            }
            //System.out.println();

            for(int i=0; i<numberOfTestCases; i++)
            {
                String [] inputs = input[i][0].split(" ");
                int numberOfInputs = Integer.parseInt(inputs[0].trim());
                int numberOfOutputsRequired = Integer.parseInt(inputs[1].trim());
                if(numberOfInputs == 1)
                {
                    String sampleInput = input[i][1].trim();
                    StringBuilder out = new StringBuilder();
                    for(int j=0; j<numberOfOutputsRequired-1; j++)
                    {
                      out.append(sampleInput).append(" ");
                    }
                    out.append(sampleInput);
                    System.out.println(out.toString());
                }
                else
                {
                    String [] sampleInputText = input[i][1].split(" ");
                    int []  sampleInput = new int[sampleInputText.length];
                    int count = 0;
                    for(String in: sampleInputText)
                    {
                        sampleInput[count++] = Integer.parseInt(in);
                    }
                    int [] inputCopy = Arrays.copyOf(sampleInput, sampleInput.length);
                    int start = 1;
                    int polynomialOrder = 0;

                    while(!notAllEqual(inputCopy, start))
                    {
                        int prev = inputCopy[start-1];
                        int current = 0;

                        for(int k=start; k<inputCopy.length; k++)
                        {
                            current = inputCopy[k];
                            inputCopy[k] = inputCopy[k]-prev;
                            prev = current;
                        }
                        start++;
                        polynomialOrder++;
                    }
                    int [] polynomialEquation = getEquationCoefficients(sampleInput, polynomialOrder);
                }
            }
        }
        catch(Exception exc)
        {
          exc.printStackTrace();
        }
    }

    private static int[] getEquationCoefficients(int[] sampleInput, int polynomialOrder) {
        int [] result = new int[polynomialOrder+1];
        //if polynomialOrder is 3 the result array should be of size 4 containing a,b,c,d of ax3+ bx2+ cx+ d

        result[0] = sampleInput[0];//value of d when x is 0


        return result;
    }

    private static boolean notAllEqual(int[] inputCopy, int start) {
        boolean result = true;

        for(int i=start; i<inputCopy.length-1; i++)
        {
            result = result && (inputCopy[start]== inputCopy[start+1]);
            if(!result)
            {
                break;
            }
        }

        return result;
    }
}
