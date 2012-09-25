package com.dev.saurabh;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 9/25/12
 * Time: 11:23 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TreeGame {

    private static int [] inputTree = null;

    public static void main(String[] args) throws Exception{

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        int treeHeight = Integer.parseInt(consoleReader.readLine());
        int leafCount = 1<<treeHeight;//as question says that the number of leafs will be 2^h at this level
        int maxTreeNodes = leafCount << 1;
        inputTree = new int[maxTreeNodes];
        String [] input = consoleReader.readLine().split(" ");

        int [] leafNodes = new int[input.length-1];//we do not need the last input
        int startIndex = 1;
        int endIndex = leafCount;
        int maxSumOfLeafsLookingFor = 2;
        for(int i=0; i<leafNodes.length; i++)
        {
            leafNodes[i] = Integer.parseInt(input[i]);
            if(i > 0)
            {
                System.out.print(" ");
            }
            //idea is to confuse the parent node. I mean make it mandatory for the internal node
            //to read both the child nodes to deduce its value. This is only possible when atleast one of the
            //leaf node contains a 1.
            displayTheOutput(1, leafNodes[i], maxSumOfLeafsLookingFor, startIndex, endIndex);
        }

    }

    private static void displayTheOutput(int parentNodeIndex, int currentNodeIndex,
                                         int maxSumOfLeafsLookingFor, int startIndex, int endIndex) {

        if(startIndex == endIndex)
        {
            inputTree[parentNodeIndex] = maxSumOfLeafsLookingFor;
            System.out.print(maxSumOfLeafsLookingFor-1);
            return;
        }
        int lChildLocation = parentNodeIndex<<1;
        int tempStart = inputTree[lChildLocation];
        int tempEnd = inputTree[lChildLocation+1];
        int mid = (startIndex+endIndex)>>1;
        int currentVal = 1;//means off or 0
        if((maxSumOfLeafsLookingFor == 1) || (tempStart == 0 && tempEnd == 0))
        {
            currentVal = 2;//means on or 1
        }
        if(currentNodeIndex <= mid)
        {
            displayTheOutput(lChildLocation, currentNodeIndex, currentVal, startIndex, mid);
        }
        else
        {
            displayTheOutput(lChildLocation+1, currentNodeIndex, currentVal, mid+1, endIndex);
        }

        tempStart = inputTree[lChildLocation];
        tempEnd = inputTree[lChildLocation+1];
        if(tempStart != 0 && tempEnd != 0)
        {
            inputTree[parentNodeIndex] = maxSumOfLeafsLookingFor;
        }
    }
}

