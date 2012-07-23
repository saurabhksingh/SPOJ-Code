package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: skumarsi
 * Date: 23/7/12
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem43 {

    private static long maxBookCount = 0;
    private static int scribeCount=0;
    private static boolean firstLineGap = true;

    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            int testCaseCount = Integer.parseInt(consoleReader.readLine());

            for(int i=testCaseCount; i>0; i--)
            {
                String [] input = consoleReader.readLine().split(" ");
                int bookCount = Integer.parseInt(input[0]);
                scribeCount = Integer.parseInt(input[1]);

                long [] pages = new long[bookCount];

                populatePageCount(pages, consoleReader, bookCount);

                if(firstLineGap)
                {
                    firstLineGap = false;
                    System.out.println();
                }

                if(scribeCount == 1)
                {
                   for(int j=0; j<bookCount-1; j++)
                   {
                       System.out.print(pages[j]);System.out.print(" ");
                   }
                   System.out.println(pages[bookCount-1]);
                   continue;
                }
                //at this stage we have input for thr current test case and the maximum
                //load which can be beared by a scribe or a group of scribes

                //now by going from right to left we need to put the dividers such that no group
                //of scribes exceed maxBookCount
                int [] dividePoints = new int[scribeCount-1];
                int count = scribeCount-2;

                for(int j=bookCount-1; j>=0;)
                {
                    long currentSum = 0;
                    if(j == count+1)
                    {
                        while(count >= 0)
                        {
                            dividePoints[count--] = j-1;
                            j--;
                        }

                        break;
                    }
                    else
                    {
                        while(j>count && currentSum < maxBookCount)
                        {
                            currentSum += pages[j];
                            j--;
                        }

                        if(currentSum > maxBookCount)
                        {
                            j++;
                        }
                        // System.out.println(j);
                        dividePoints[count] = j;
                        count--;
                        if(count < 0)break;
                    }
                }
                int previous = 0;
                for(int dividePoint : dividePoints)
                {
                    for(int j=previous;j<=dividePoint;j++)
                    {
                       System.out.print(pages[j]);System.out.print(" ");
                    }
                   if(dividePoint<bookCount)
                   {
                       System.out.print("/ ");
                       previous = dividePoint+1;
                   }
                   else
                   {
                       previous =  bookCount;
                   }
                }
                for(int j=previous; j<bookCount-1;j++)
                {
                    System.out.print(pages[j]);System.out.print(" ");
                }
               if(previous < bookCount)
               {
                   System.out.print(pages[bookCount-1]);
                   System.out.println();
               }
            }

        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private static void populatePageCount(long[] pages, BufferedReader consoleReader, int bookCount) throws Exception{
        int intch;
        StringBuilder out = new StringBuilder();
        int count = 0;
        long low = Long.MIN_VALUE;
        long high = 0;
        while (count <bookCount && (char)(intch = consoleReader.read()) != '\n') {
            int ch = (char) intch;
            if(ch !=' ')
            {
                out.append(ch-'0');
            }
            else
            {
                pages[count++] = Long.valueOf(out.toString()) ;
                if(pages[count-1] > low)
                {
                    low = pages[count-1];
                }
                high += pages[count-1];
                out = new StringBuilder();
            }

        }
        if(count < bookCount)
        {
            pages[count] = Integer.valueOf(out.toString());
            if(pages[count] > low)
            {
                low = pages[count];
            }
            high += pages[count];
        }
        maxBookCount = getMaxAllocatedSum(pages, low, high);
      //  System.out.println("Max load to a scribe can be :"+ maxBookCount +" Low is : "+low +" and high is : "+high);
    }

    private static long getMaxAllocatedSum(long[] pages, long low, long high) {
        while ( low < high )
        {
            long mid = low + (high-low)/2;
            int allotedScribeCount = 1;
            long currentLoad = 0;
            for ( int i=0; i<pages.length; ++i )
            {
                if ( currentLoad + pages[i] <= mid )
                {
                    currentLoad += pages[i];
                }
                else
                {
                    ++allotedScribeCount;
                    currentLoad = pages[i];
                }
            }

            if ( allotedScribeCount <= scribeCount )
            {
                high = mid;
            }
            else
            {
                low = mid+1;
            }
        }
        return low;
    }
}
