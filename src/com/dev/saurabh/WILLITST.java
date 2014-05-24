package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by ssaurab on 2/10/14.
 */
public class WILLITST {

    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            BigInteger number = new BigInteger(consoleReader.readLine());
            boolean isPowerOfTwo = (number.and(number.subtract(BigInteger.ONE))).equals(BigInteger.ZERO);
            if(isPowerOfTwo){
                System.out.println("TAK");
            }
            else{
                System.out.println("NIE");
            }
        }
        catch(Exception exc){

        }
    }
}
