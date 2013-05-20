package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PHONELST
{
    public static void main(String [] args)
    {

        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int testCases = Integer.parseInt(consoleReader.readLine());

            for(int i=0; i<testCases; i++)
            {
                int phoneCount = Integer.parseInt(consoleReader.readLine());
                Trie trie = new Trie();
                boolean isConsistent = true;
                String [] phoneNumbers = new String[phoneCount];
                for(int j=0; j<phoneCount; j++)
                {
                    phoneNumbers[j] = consoleReader.readLine().trim();
                }
                Arrays.sort(phoneNumbers);
                for(int j=0; j<phoneCount-1; j++)
                {
                    if(phoneNumbers[j+1].startsWith(phoneNumbers[j]))
                    {
                        isConsistent = false;
                        break;
                    }
//                    isConsistent = trie.addPhoneNumber(phone);
//                    if(!isConsistent)break;
                }
                String result = isConsistent ? "YES":"NO";
                System.out.println(result);
            }
        }
        catch(Exception ex)
        {

        }
    }

    private static class Trie
    {
        boolean isPhoneNumber;
        Trie[] links;

        public Trie()
        {
            isPhoneNumber = false;
            links = new Trie[10];
        }

        public boolean addPhoneNumber(String s)
        {
            Trie t = this;
            int limit = s.length();
            boolean newNodeCreated = false;
            for(int k=0; k < limit; k++)
            {
                int index = s.charAt(k) - '0';
                if (t.links[index] == null)
                {
                    newNodeCreated = true;
                    t.links[index] = new Trie();
                }

                t = t.links[index];
                if(t.isPhoneNumber && !newNodeCreated) return false;
            }

            if(t.isPhoneNumber && !newNodeCreated) return false;

            t.isPhoneNumber = true;
            return true;
        }
    }
}
