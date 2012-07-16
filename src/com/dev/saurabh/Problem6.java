package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/12/12
 * Time: 12:38 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem6 {

    private static final String pattern = "['\\+','\\-','\\*']";

    public static void main(String [] args)
    {
        /**
         * One part of the new WAP portal is also a calculator computing expressions with very long numbers.
         To make the output look better, the result is formatted the same way as is it usually used with manual
         calculations.
         Your task is to write the core part of this calculator. Given two numbers and the requested operation,
         you are to compute the result and print it in the form specified below. With addition and subtraction,
         the numbers are written below each other. Multiplication is a little bit more complex: first of all, we
         make a partial result for every digit of one of the numbers, and then sum the results together.
         */

        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            int numOfTestCases = Integer.parseInt(consoleReader.readLine());
            List<String> expressionsList = new ArrayList<String>();
            for(int i=0 ;i<numOfTestCases; i++)
            {
                expressionsList.add(consoleReader.readLine());
            }
            System.out.println();
            int counter = 1;

            for(String expression : expressionsList)
            {
                String [] operands = expression.split(pattern);
                int index = expression.lastIndexOf(operands[1]);
                char operator = expression.substring(index-1, index).toCharArray()[0];
                String firstOperand = operands[0];
                String secondOperand = operands[1];
                StringBuilder firstLine = new StringBuilder();
                StringBuilder secondLine = new StringBuilder();

                firstLine.append(firstOperand);
                secondLine.append(operator);secondLine.append(secondOperand);
                String result = "";
                char [] firstOperandCharArray = firstOperand.toCharArray();
                char [] secondOperandCharArray = secondOperand.toCharArray();
                //System.out.println(expressionsList.size());
                switch(operator)
                {
                    case '+':
                        result = add(firstOperandCharArray, secondOperandCharArray);
                        displayFormattedOutput(firstLine, secondLine, result, (counter==expressionsList.size()));
                        break;
                    case '-':
                        result = sub(firstOperandCharArray, secondOperandCharArray);
                        displayFormattedOutput(firstLine, secondLine, result, (counter==expressionsList.size()));
                        break;
                    case '*':
                        String [] results ;
                        StringBuilder sum = new StringBuilder();
                        results = multiply(firstOperandCharArray, secondOperandCharArray, sum);
                        displayFormattedOutput(firstLine, secondLine, results, sum, (counter==expressionsList.size()));
                        break;
                }
                counter++;
               // System.out.println();
            }
            //System.out.println();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private static String[] multiply(char[] firstOperandCharArray, char[] secondOperandCharArray, StringBuilder result) {
        String [] cache = new String[10];
        for(int i=0; i<cache.length; i++)
        {
            cache[i]  = null;
        }
        String out="";
        String [] intermediateOutput = new String [secondOperandCharArray.length];
        int count = 0;
        for(int i = secondOperandCharArray.length-1; i>=0; i--)
        {
            int carry = 0;
            int num = secondOperandCharArray[i]-'0';
            String sum="";
            if(num == 0)
            {
                sum = ""+0;
                StringBuilder temp = new StringBuilder();
                for(int k=0; k<secondOperandCharArray.length-1; k++)
                {
                    temp.append(" ");
                }
                temp.append(0);
                intermediateOutput[count++] = temp.toString();
            }
            else
            {
                if(cache[num] != null)
                {
                    sum = cache[num];
                }
                else
                {
                    for(int j = firstOperandCharArray.length-1; j>=0 ; j--)
                    {
                        int mul = num * (firstOperandCharArray[j] - '0') + carry;
                        int digit= mul%10;
                        carry = mul/10;
                        sum = digit+sum;
                    }
                }

            }

            if(carry > 0)
            {
                sum = carry+sum;
            }
            cache[num] = sum;
            if(num != 0)
            {
                intermediateOutput[count++] = sum;
            }
        }
        BigInteger first= new BigInteger(new String(firstOperandCharArray));
        BigInteger second = new BigInteger(new String(secondOperandCharArray));
        result.append(first.multiply(second));

        return intermediateOutput;
    }

    private static void indent(String[] intermediateOutput)
    {
        for(int i=0; i<intermediateOutput.length; i++)
        {

            intermediateOutput[i] = " "+intermediateOutput[i];
        }
    }

    private static void displayFormattedOutput(StringBuilder firstLine, StringBuilder secondLine, String[] result, StringBuilder sum, boolean isLastOut)
    {
        int resultLength = sum.length();// result[0].length()+result.length-1;
        int firstDashLength = result[0].trim().length();
        int highestLength = (resultLength >= firstLine.length())?(resultLength >= secondLine.length() ? resultLength : secondLine.length()):
                (firstLine.length() >= secondLine.length() ? firstLine.length() : secondLine.length());
        firstDashLength = (firstDashLength >= firstLine.length())?(firstDashLength >= secondLine.length() ? firstDashLength : secondLine.length()):
                (firstLine.length() >= secondLine.length() ? firstLine.length() : secondLine.length());

        StringBuilder out = new StringBuilder();
        for(int i=0; i<highestLength-firstLine.length(); i++)
        {
            out.append(" ");
        }

        out.append(firstLine).append("\n");
        for(int i=0; i<highestLength-secondLine.length(); i++)
        {
            out.append(" ");
        }

        out.append(secondLine).append("\n");
        for(int i=0; i<(highestLength-firstDashLength); i++)
        {
            out.append(" ");
        }
        for(int i=0; i<firstDashLength; i++)
        {
            out.append("-");
        }
        out.append("\n");

        int count = 1;
        for(String str : result)
        {
            int strLen = str.length();
            for(int i=((highestLength-strLen)-(count++)); i>=0; i--)
            {
                out.append(" ");
            }
            out.append(str);
            if(result.length>1)out.append("\n");
        }
        if(result.length >  1)
        {
            for(int i=0; i<highestLength; i++)
            {
                out.append("-");
            }
            out.append("\n");
            for(int i=0; i<highestLength-sum.length(); i++)
            {
                //System.out.print(" ");
                out.append(" ");
            }
            //System.out.print(sum);
            out.append(sum);
            if(!isLastOut)out.append("\n");
        }

        System.out.println(out.toString()) ;
       // else  System.out.print(out);
    }

    private static void displayFormattedOutput(StringBuilder firstLine, StringBuilder secondLine, String result, boolean isLastOut)
    {
        int resultLength = result.length();
        int highestLength = (resultLength >= firstLine.length())?(resultLength >= secondLine.length() ? resultLength : secondLine.length()):
                (firstLine.length() >= secondLine.length() ? firstLine.length() : secondLine.length());
        StringBuilder out = new StringBuilder();
        for(int i=0; i<highestLength-firstLine.length(); i++)
        {
            //System.out.print(" ");
            out.append(" ");
        }
        //System.out.println(firstLine);
        out.append(firstLine).append("\n");
        for(int i=0; i<highestLength-secondLine.length(); i++)
        {
            //System.out.print(" ");
            out.append(" ");
        }
        //System.out.println(secondLine);
        out.append(secondLine).append("\n");
        for(int i=0; i<highestLength; i++)
        {
            //System.out.print("-");
            out.append("-");
        }
        //System.out.println();
        out.append("\n");
        for(int i=0; i<highestLength-resultLength; i++)
        {
            //System.out.print(" ");
            out.append(" ");
        }
        //System.out.println(result);
        out.append(result);
        if(!isLastOut)out.append("\n");
        System.out.println(out);
    }

    private static String add(char [] firstCharArray, char [] secondCharArray)
    {
        int carry = 0;

        int firstLen = firstCharArray.length-1;
        int secondLen = secondCharArray.length-1;
        char [] result;
        int resultLen;
        if(firstLen > secondLen)
        {
            result = firstCharArray;
            resultLen = firstLen;
        }
        else
        {
            result = secondCharArray;
            resultLen = secondLen;
        }
        while(firstLen >=0 && secondLen >=0)
        {
            int sum = (firstCharArray[firstLen--]-'0' + secondCharArray[secondLen--]-'0')+carry;
            int unitDigit = sum % 10;
            result[resultLen--] = (char)(unitDigit+'0');

            carry  = sum/10;

        }
        if(firstLen >= 0)
        {
            while(firstLen>=0)
            {
                int sum = (firstCharArray[firstLen--]-'0')+carry;
                int unitDigit = sum % 10;
                result[resultLen--] = (char)(unitDigit+'0');

                carry  = sum/10;
            }
        }
        else if(secondLen >= 0)
        {
            while(secondLen>=0)
            {
                int sum = (secondCharArray[secondLen--]-'0')+carry;
                int unitDigit = sum % 10;
                result[resultLen--] = (char)(unitDigit+'0');

                carry  = sum/10;
            }
        }
        StringBuilder out = new StringBuilder();
        if(carry > 0)
        {
            out.append(carry);
        }
        out.append(result);

        return out.toString();
    }

    private static String sub(char[]firstOperand, char[] secondOperand)
    {
        StringBuilder result = new StringBuilder();
        int firstOffset = 0;
        int secondOffset = 0;

        while(firstOperand[firstOffset] == 0 && firstOffset<firstOperand.length)
        {
            firstOffset++;
        }
        while(secondOperand[secondOffset] == 0 && secondOffset<secondOperand.length)
        {
            secondOffset++;
        }

        if((firstOperand.length-firstOffset) <  (secondOperand.length-secondOffset))
        {
            //swap the two to get proper result
            char [] temp = firstOperand;
            firstOperand = secondOperand;
            secondOperand = temp;
            result.append("-");
        }
        else if((firstOperand.length-firstOffset) == (secondOperand.length-secondOffset))
        {
            //swap if the highest position digit of second is bigger than that of first
            if(firstOperand[0] < secondOperand[0])
            {
                char [] temp = firstOperand;
                firstOperand = secondOperand;
                secondOperand = temp;
                result.append("-");
            }
            else
            {
                for(int i=1; i<firstOperand.length; i++)
                {
                    if(firstOperand[i] == secondOperand[i])
                    {
                        continue;
                    }
                    else
                    {
                        if(firstOperand[i] > secondOperand[i])
                        {
                            break;
                        }
                        else
                        {
                            char [] temp = firstOperand;
                            firstOperand = secondOperand;
                            secondOperand = temp;
                            result.append("-");
                            break;
                        }
                    }
                }
            }
        }

        int firstLen = firstOperand.length-1;
        int secondLen = secondOperand.length-1;
        int resultLen;
        int borrow  = 0;
        while(firstLen >=0 && secondLen >=0)
        {
            int diff = (firstOperand[firstLen]- secondOperand[secondLen]) - borrow;
            if(diff < 0)
            {
                diff += 10;
                borrow = 1;
            }
            else
            {
                borrow = 0;
            }
            firstOperand[firstLen] = (char)(diff+'0');
            firstLen--;
            secondLen--;
        }
        if(firstLen >= 0)
        {
            while(firstLen>=0)
            {
                int diff = (firstOperand[firstLen]-'0')-borrow;
                if(diff < 0)
                {
                    diff += 10;
                    borrow = 1;
                }
                else
                {
                    borrow = 0;
                }
                firstOperand[firstLen] = (char)(diff+'0');
                firstLen--;
            }

        }
        int nonZeroOccurrenceIndex = -1;
        for(int i=0; i<firstOperand.length;i++)
        {
            if(firstOperand[i] != '0')
            {
                nonZeroOccurrenceIndex = i;
                break;
            }
        }
        if(nonZeroOccurrenceIndex < 0)
        {
            result.append(0);
        }
        else
        {
            for(int i=nonZeroOccurrenceIndex; i<firstOperand.length; i++)
            {
                result.append(firstOperand[i]);
            }
        }

        return result.toString();
    }
}
