package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaurab on 5/24/14.
 */
public class NDIV {

    private static Map<Long, Long> cache = new HashMap<Long, Long>();
    private static final int N = 32000;
    private static int [] primes = new int[N];
    private static boolean[] isPrime = new boolean[N + 1];
    private static int totalPrimes;

    static{

        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }


        for (int i = 2; i*i <= N; i++) {

            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= N; j++) {
                    isPrime[i*j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes[count ++ ] = i;
                totalPrimes++;
            }
        }
    }

    public static void main(String [] args){

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String [] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int n = Integer.parseInt(input[2]);

            int factorCount=0,pow=0;
            for(int i=a;i<=b;i++)
            {
                int numToCheck=i,ans=1;
                for(int j=0,primeNum=primes[j];j<totalPrimes; primeNum = primes[++j])
                {
                    pow=0;
                    if(numToCheck%primeNum==0)
                    {

                        while(numToCheck%primeNum==0)
                        {
                            pow++;
                            numToCheck/=primeNum;
                        }

                    }
                    ans=ans*(pow+1);
                    if(numToCheck==1)
                        break;
                }
                if(numToCheck != 1){
                    ans = ans*2;
                }
                if(ans==n)
                    factorCount++;
            }


            System.out.println(factorCount);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    private static long getFactors(long num, long n) {
        long factorsCount = 1;
        for (int i = 2; i * i <= num; ++i) // for each number i up until the square root of the given number
        {
            int power = 0;
            while (num % i == 0)
            {
                num = num / i;
                ++power;
            }
            factorsCount = factorsCount * (power + 1) ;// apply the formula

            if(factorsCount > n) {
                return -1;
            }
        }

        if (num > 1) // will happen for example for 14 = 2 * 7
        {
            factorsCount = factorsCount * 2 ;// n is prime, and its power can only be 1, so multiply the number of factors by 2
        }

        return factorsCount;
    }
}