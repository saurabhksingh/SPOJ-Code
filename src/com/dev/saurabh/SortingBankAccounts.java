package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/19/12
 * Time: 12:11 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class SortingBankAccounts {

    /**
     * We will be creating Trie to handle the problem
     */

    private class TrieNode
    {
        boolean isLeafNode;
        char myDigit;
        int myFrequency;

        TrieNode [] myLinks;

        public TrieNode(char digit)
        {
            myDigit = digit;
            myFrequency = 0;
            myLinks = null;
        }

        public void add(char [] data, int pos)
        {
            if(data == null || pos>=data.length)
            {
                return;
            }
            if(myLinks == null)
            {
                myLinks = new TrieNode[10];
            }

            char ch = data[pos];
            TrieNode node = myLinks[ch-'0'];
            if(node == null)
            {
                node = new TrieNode(ch);
                myLinks[ch-'0'] = node;
            }

            if(pos == data.length-1)
            {
                myFrequency++;
            }
            else
            {
                add(data, pos++);
            }
        }

        public String getSortedAccountNumbersWithFreq()
        {
            StringBuilder out = new StringBuilder();

            for(int i=9; i>=0; i--)
            {
                if(myLinks[i] != null)
                {
                  //start fetching the data

                }

            }

            return out.toString();
        }
    }

    public static void main(String [] args)
    {

        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int numberOfTestCases = Integer.parseInt(consoleReader.readLine());
            StringBuilder out = new StringBuilder();

            while(numberOfTestCases != 0)
            {
              String bankAccountsIn = consoleReader.readLine();
              if(!"".equals(bankAccountsIn))
              {
                  int bankAccounts = Integer.parseInt(bankAccountsIn);
                  numberOfTestCases--;

                  //String [] accntNumber = new String[bankAccounts];
                  for(int j = bankAccounts; j>0; j--)
                  {
                      String accnt = consoleReader.readLine().trim();
                      String [] accntParts = accnt.split(" ");

                  }
              }
              //consoleReader.readLine();
            }
            System.out.println();
            System.out.print(out);

        }
        catch(Exception exc)
        {
           exc.printStackTrace();
        }
    }
}
