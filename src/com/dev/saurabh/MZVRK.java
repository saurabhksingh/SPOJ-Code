package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by ssaurab on 5/23/14.
 */
public class MZVRK {

    private static final BigInteger TWO = new BigInteger("2");
    public static void main(String [] args){

    try{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] input = br.readLine().split(" ");
        BigInteger a = new BigInteger(input[0]);
        BigInteger b = new BigInteger(input[1]);

        long result = 0;

        for(BigInteger start = a; start.compareTo(b) <=0;){

            int pos = getPositionOfRightMostOne(start);
            result += 1<<pos;
            start = start.add(BigInteger.ONE);
        }

        System.out.println(result);

    }
    catch (Exception exc){
        exc.printStackTrace();
    }
}

    private static int getPositionOfRightMostOne(BigInteger bigInt) {
        int result = 0;
        while(!bigInt.mod(TWO).equals(BigInteger.ONE)){
            bigInt  = bigInt.shiftRight(1);
            result ++;
        }

        return result;
    }

    private static int getGCD(int a, int b) {
        int p = Math.min(Math.abs(a), Math.abs(b));
        int q = Math.max(Math.abs(a), Math.abs(b));

        if(p == 0) return q;
        return getGCD(p, q % p);
    }
}

