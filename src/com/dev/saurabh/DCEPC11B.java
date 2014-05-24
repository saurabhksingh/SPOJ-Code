package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 5/24/14.
 */
public class DCEPC11B {

    public static void main(String [] args){

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int totalNumberOfCases = Integer.parseInt(br.readLine());
            for(int k=0; k<totalNumberOfCases; k++){

                String [] input = br.readLine().split(" ");
                int n = Integer.parseInt(input[0]);
                int p = Integer.parseInt(input[1]);

                if(n > p){
                    System.out.println(0);
                }
                else if(n == 1){
                    System.out.println(1);
                }
                else if(n == p-1){
                    System.out.println(p-1);
                }
                else {
                    long res = 1;
                    for(int i=n+1;i <= p-2 ;i++)
                        res = (res*i)%p;


                    res = getModForPerm(res, p - 2, p);

                    System.out.println(res);
                }
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    private static long getModForPerm(long a, long b, long mod) {

        long res = 0;
        if(b==0)
            return 1;
        if(b==1)
            return a;
        res = getModForPerm(a, b / 2, mod)%mod;
        if(b%2==0)
            return (res*res)%mod;
        else
            return ( ( (res*res)%mod)*a)%mod;
    }
}