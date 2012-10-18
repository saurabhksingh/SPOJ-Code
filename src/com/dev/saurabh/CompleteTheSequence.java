package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/17/12
 * Time: 5:05 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class CompleteTheSequence
{
    /**
     *
     * I will be making use of method of finite differences:
     * (http://en.wikipedia.org/wiki/Difference_engine#Method_of_differences)
     */
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
            System.out.println();

            for(int i=0; i<numberOfTestCases; i++)
            {
                String [] inputs = input[i][0].split(" ");
                int numberOfInputs = Integer.parseInt(inputs[0].trim());
                int numberOfOutputsRequired = Integer.parseInt(inputs[1].trim());
                StringBuilder out = new StringBuilder();

                if(numberOfInputs == 1)
                {
                    String sampleInput = input[i][1].trim();

                    for(int j=0; j<numberOfOutputsRequired-1; j++)
                    {
                      out.append(sampleInput).append(" ");
                    }
                    out.append(sampleInput);
                    System.out.println(out.toString());
                }
                else
                {
                    ArrayList <Long> diagonalNumbers = new ArrayList<Long>();
                    int numberOfOutputs = Integer.parseInt(input[i][0].split(" ")[1]);
                    String [] sampleInputText = input[i][1].split(" ");
                    long []  sampleInput = new long[sampleInputText.length];
                    int count = 0;
                    for(String in: sampleInputText)
                    {
                        sampleInput[count++] = Integer.parseInt(in);
                    }
                    long [] inputCopy = Arrays.copyOf(sampleInput, sampleInput.length);
                    int start = 1;
                    int polynomialOrder = 0;
                    diagonalNumbers.add(inputCopy[inputCopy.length-1]);
                    int length = inputCopy.length;
                    while(!notAllEqual(inputCopy, length))
                    {
                        for(int k=0; k<length-1; k++)
                        {
                            inputCopy[k] = inputCopy[k+1]  - inputCopy[k];
                        }
                        start++;
                       // System.out.println(inputCopy[length-2]);
                        diagonalNumbers.add(inputCopy[length-2]);
                        length--;
                        polynomialOrder++;
                    }
                   // diagonalNumbers.add(inputCopy[0]);
                    //System.out.println(inputCopy[0]);

                    for(int k=0; k<numberOfOutputs; k++)
                    {
                       long prev = diagonalNumbers.get(diagonalNumbers.size()-1);
                       for(int l=diagonalNumbers.size()-2; l>=0; l--)
                       {
                           prev += diagonalNumbers.get(l);
                           diagonalNumbers.set(l,prev);
                       }
                       out.append(prev).append(" ");
                    }
                    System.out.println(out);
                }
            }
        }
        catch(Exception exc)
        {
          exc.printStackTrace();
        }
    }

    private static boolean notAllEqual(long[] inputCopy, long length) {
        boolean result = true;
        if(length > 1)
        {
            for(long i=0; i<length-1; i++)
            {
                result = result && (inputCopy[(int)i]== inputCopy[(int)i+1]);
                if(!result)
                {
                    break;
                }
            }
        }

        return result;
    }
}
