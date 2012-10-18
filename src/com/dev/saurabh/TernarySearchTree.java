package com.dev.saurabh;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/20/12
 * Time: 12:05 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
class TernaryNode
{
    char myDigit;
    TernaryNode left, center, right;
    boolean accountNumberEnd;
    int myFrequency = 0;

    public TernaryNode(char digit, boolean isEnd)
    {
        myDigit = digit;
        accountNumberEnd = isEnd;
    }
}

public class TernarySearchTree
{
    private TernaryNode myRoot;

    public TernarySearchTree()
    {
        myRoot = null;
    }

    public void add(char [] data, int pos, TernaryNode node)
    {
        if (node == null)
        {
            node = new TernaryNode(data[pos], false);
        }

        if (data[pos] < node.myDigit)
        {
            add(data, pos, node.left);
        }
        else if (data[pos] > node.myDigit)
        {
            add(data, pos, node.right);
        }
        else
        {
            if (pos + 1 == data.length)
            {
                node.accountNumberEnd = true;
                node.myFrequency++;
            }
            else
            {
                add(data, pos + 1, node.center);
            }
        }
    }

    public void add(String data)
    {
        add(data.toCharArray(), 0, myRoot);
    }

    public boolean containsData(String input)
    {
        int pos = 0;
        TernaryNode node = this.myRoot;
        char data[] = input.toCharArray();
        while (node != null)
        {

            if (data[pos] < node.myDigit)
            {
                //go left
                node = node.left;
            }
            else if (data[pos] > node.myDigit)
            {
                //go right
                node = node.right;
            }
            else
            {
                if (++pos == data.length)
                {
                    return node.accountNumberEnd;
                }

                node = node.center;
            }
        }

        return false;
    }
}
