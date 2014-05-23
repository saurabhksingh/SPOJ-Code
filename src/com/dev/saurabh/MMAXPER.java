package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 2/11/14.
 */
public class MMAXPER {

    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int testCases = Integer.parseInt(consoleReader.readLine());

            for(int i=0; i<testCases; i++){
                String [] pair = consoleReader.readLine().split(" ");
                int x = Integer.parseInt(pair[0]);
                int y = Integer.parseInt(pair[1]);

                if((x == y) || (x-2==y)){
                    if(x%2 == 0){
                        System.out.println(x+y);
                    }
                    else{
                        System.out.println(x+y-1);
                    }
                }
                else{
                    System.out.println("No Number");
                }

            }

        }
        catch(Exception exc){

        }
    }
}
