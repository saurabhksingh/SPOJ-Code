package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/24/12
 * Time: 12:34 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class SubstitutionCipher {
    private static final String ERROR_MESSAGE="Message cannot be decrypted.";
    private static boolean firstOutput = true;

    private static boolean  [][] alphabetsAdjMatrix ;
    private static int level = 0;
    private static int[] V;
    private static int[] inverseSubstitute;
    private static int[] substitute;
    private static int alphabetAvailableCount;
    private static int[] depth;
    private static int[] result;
    private static boolean[] isValidRank;

    private static class Item implements Comparable<Item>
    {
        int count;
        int index;

        public Item(int cnt, int idx)
        {
            count = cnt;
            index = idx;
        }

        @Override
        public int compareTo(Item o) {
            return count-o.count;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
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
                    firstOutput = false;
                }

                //resetVariables();
                System.out.println(getDecyptedMessage(lexicallyOrdered, encryptedMessage));

            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private static String getDecyptedMessage(String[] lexicallyOrdered, String encryptedMessage)
    {
        StringBuilder out = new StringBuilder();

        substitute =  new int[26];
        inverseSubstitute = new int[26];
        result = new int[26];
        isValidRank = new boolean[26];
        depth = new int[26];
        alphabetsAdjMatrix  = new boolean[26][26];

        for(int i=0; i<26; i++)
        {
            substitute[i] = i;
            inverseSubstitute[i] = i;
            result[i] = 0;
            isValidRank[i] = false;
            depth[i] = 0;
            if (i >= alphabetAvailableCount && i < 25)
            {
                alphabetsAdjMatrix[i][i+1] = true;
            }
            else if (i < alphabetAvailableCount && alphabetAvailableCount < 26)
            {
                alphabetsAdjMatrix[i][alphabetAvailableCount] = true;
            }
        }
        //  alphabetsAdjMatrix[i][j]  = true will mean that (char)i+'alphabetAvailableCount' < (char)j+'alphabetAvailableCount'
        for(int i=0; i<lexicallyOrdered.length-1; i++)
        {
            String first = lexicallyOrdered[i];
            String second = lexicallyOrdered[i+1];
            char firstChar = ' ';
            char secondChar = ' ';
            int count = 0;
            while(count<first.length() && count<second.length())
            {
                if(first.charAt(count) == second.charAt(count))
                {
                    count++;
                }
                else
                {
                    firstChar =  first.charAt(count);
                    secondChar = second.charAt(count);
                    if(firstChar>='a' && secondChar>='a' && firstChar<='z' && secondChar<='z')
                    {
                        alphabetsAdjMatrix[firstChar-'a'][secondChar-'a'] = true;
                        break;
                    }
                }
            }
        }

        V = new int[26];
        //for(int i=0; i<26;i++)V[i]=0;
        level = 0;
        for(int v=0; v< alphabetAvailableCount; v++)
        {
            if (V[v] == 0)
            {
                dfsCreateSubstAndInvSubst(v);
            }
        }

        for(int v=0; v< alphabetAvailableCount; v++)
        {
            V = new int[26];
            //for(int i=0; i<26;i++)V[i]=0;
            result[v] = alphabetAvailableCount - dfsCount(alphabetsAdjMatrix, inverseSubstitute[v]);
        }

        for(int v=0; v<26; v++)
        {
            if (v >= alphabetAvailableCount) {
                isValidRank[v] = true;
            } else {
                isValidRank[v] = (result[v] == v && depth[inverseSubstitute[v]] == v);
            }
        }

        for(int j=0; j<encryptedMessage.length(); j++)
        {
            char ch = encryptedMessage.charAt(j);
            if(ch>='a' && ch<='z')
            {
                int enc = ch-'a';
                int dec = substitute[enc];
                if(isValidRank[dec])
                {
                    out.append((char)(dec+'a'));
                }
                else
                {
                    return ERROR_MESSAGE;
                }
            }
            else
            {
                out.append(ch);
            }
        }

        return out.toString();
    }

    private static void dfsCreateSubstAndInvSubst(int u) {
        V[u] = 1;
        for(int v =0; v<26; v++)
        {
            if (alphabetsAdjMatrix[u][v]) {
                if (V[v] == 0) {
                    dfsCreateSubstAndInvSubst(v);
                }
            }
        }
        inverseSubstitute[25-level] = u;
        substitute[u] = 25 - level;
        level++;
    }

    private static int dfsCount(boolean [][]adjMatrix, int u) {
        V[u] = 1;
        for(int v =0; v< alphabetAvailableCount; v++) {
            if (adjMatrix[u][v]) {
                if (V[v] == 0) {
                    depth[v]++;
                    V[u] += dfsCount(adjMatrix, v);
                }
            }
        }
        return V[u];
    }
}
