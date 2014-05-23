package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by ssaurab on 2/12/14.
 */
public class FREQUENT {

    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));

            while(true){
                String [] testCases = consoleReader.readLine().split(" ");
                int n = Integer.parseInt(testCases[0]);
                int q = Integer.parseInt(testCases[1]);

                String [] numbers = consoleReader.readLine().split(" ");
                int [] input = new int[numbers.length];
                int [] freq = new int[numbers.length];
                Arrays.fill(freq, 1);
                int count = 0;
                for(String str : numbers){
                    input[count] = Integer.parseInt(str);
                    if(count > 0){
                        if(input[count] == input[count-1]){
                            freq[count] = freq[count-1]+1;
                        }
                    }

                    count++;
                }

                for(int i=0; i<q; i++){
                    String [] range = consoleReader.readLine().split(" ");
                    int start = Integer.parseInt(range[0])-1;
                    int end = Integer.parseInt(range[1])-1;

                    int [] tempFreq = Arrays.copyOfRange(freq, start, end+1);
                    int [] temp = Arrays.copyOfRange(input, start, end+1);
                    int diff = tempFreq[0]-1;
                    tempFreq[0] = 1;
                    int k = 1;
                    while(temp[k] == temp[k-1] && k<tempFreq.length){
                        tempFreq[k] = tempFreq[k]-diff;
                        k++;
                    }
                    if(k == tempFreq.length){
                        System.out.println(tempFreq.length);
                    }
                    else{
                        Arrays.sort(tempFreq);
                        System.out.println(tempFreq[tempFreq.length-1]);
                    }
                }

            }

        }
        catch(Exception exc){

        }
    }
}
