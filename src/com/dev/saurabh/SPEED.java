package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 5/23/14.
 */
public class SPEED {

    public static void main(String [] args){

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int totalNumberOfCases = Integer.parseInt(br.readLine());
            for(int i=0; i<totalNumberOfCases; i++){

                String [] input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);

                int gcd = getGCD(a, b);

                System.out.println(Math.abs(a-b)/gcd);
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    private static int getGCD(int a, int b) {
        int p = Math.min(Math.abs(a), Math.abs(b));
        int q = Math.max(Math.abs(a), Math.abs(b));

        if(p == 0) return q;
        return getGCD(p, q % p);
    }
}
