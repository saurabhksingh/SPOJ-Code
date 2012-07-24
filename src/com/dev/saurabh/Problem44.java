package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/24/12
 * Time: 12:34 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Problem44 {
    private static final String ERROR_MESSAGE="Message cannot be decrypted.";
    private static boolean firstOutput = true;

    private static boolean  [][] alphabetsAdjMatrix ;
    private static int alphabetAvailableCount;

    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int testCaseCount = Integer.parseInt(consoleReader.readLine());
            firstOutput = true;
            for(int i=testCaseCount; i>0; i--)
            {
                String [] firstLineInput = consoleReader.readLine().split(" ");
                alphabetAvailableCount = Integer.valueOf(firstLineInput[0].trim()) ;
                int encryptedWordCount = Integer.valueOf(firstLineInput[1].trim());

                String [] lexicallyOrdered = new String[encryptedWordCount];
                for(int j=lexicallyOrdered.length; j>0; j--)
                {
                    lexicallyOrdered[lexicallyOrdered.length-j] = consoleReader.readLine();
                }
                String encryptedMessage = consoleReader.readLine();

                if(firstOutput)
                {
                    System.out.println();
                }

                //resetVariables();

                System.out.println(getDecyptedMessage(alphabetAvailableCount, lexicallyOrdered, encryptedMessage));

            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private static void resetVariables() {

        alphabetsAdjMatrix = new boolean[26][26];
        alphabetAvailableCount = 0;
    }

    private static String getDecyptedMessage(int alphabets, String[] lexicallyOrdered, String encryptedMessage) {
        char [] encryptedKeys = new char[alphabets];
        char [] decryptedKeys = new char[alphabets];
        int [] alphabetRank = new int[alphabets];
        alphabetsAdjMatrix  = new boolean[alphabets][alphabets];
        //  alphabetsAdjMatrix[i][j]  = true will mean that (char)i+'alphabetAvailableCount' < (char)j+'alphabetAvailableCount'
        for(int i=0; i<lexicallyOrdered.length-1; i++)
        {
            String first = lexicallyOrdered[i];
            String second = lexicallyOrdered[i+1];
            char firstChar = ' ';
            char secondChar = ' ';
            int count = 0;
            boolean orderFound = false;
            while(count<first.length() && count<second.length())
            {
                if(first.charAt(count) == second.charAt(count))
                {
                    count++;
                }
                else
                {
                    orderFound = true;
                    firstChar =  first.charAt(count);
                    secondChar = second.charAt(count);
                    break;
                }
            }

            if(orderFound)
            {
                alphabetsAdjMatrix[firstChar-'a'][secondChar-'a'] = true;
                alphabetRank[firstChar-'a']++;
                //look in to the row number secondChar-'a'. For all the column j which is true mark [firstChar-'a']
                //as true
                for(int j=0; j<alphabets; j++)
                {
                    if(alphabetsAdjMatrix[secondChar-'a'][j] == true)
                    {
                        if(! alphabetsAdjMatrix[firstChar-'a'][j])
                        {
                            alphabetsAdjMatrix[firstChar-'a'][j] = true;
                            alphabetRank[firstChar-'a']++;
                        }
                    }
                }
                //look in to the same column as secondChar-'a' and mark all those places as true where [i][firstChar-'a']
                //is true.
                for(int j=0; j<alphabets; j++)
                {
                    if(alphabetsAdjMatrix[j][firstChar-'a'] == true)
                    {
                        if(! alphabetsAdjMatrix[j][secondChar-'a'])
                        {
                            alphabetsAdjMatrix[j][secondChar-'a'] = true;
                            alphabetRank[j]++;
                        }
                    }
                }
            }

        }

        int order = 0;
        int count = 0;
        for(int rank : alphabetRank)
        {
            System.out.println("Rank of "+((char)(count++ +'a')) +" is: "+rank);
        }

        return ERROR_MESSAGE;
    }
}
