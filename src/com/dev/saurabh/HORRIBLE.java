package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by ssaurab on 2/12/14.
 */
public class HORRIBLE {

    private static long [] A1 = new long[100005];
    private static long [] A2 = new long[100005];
    private static int N = 0;
    /*
    This needs range update operations
     */
    public static void main(String [] args)
    {
        try
        {
            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int testCases = Integer.parseInt(consoleReader.readLine());

            for(int i=0; i<testCases; i++){
                String [] pair = consoleReader.readLine().split(" ");

                N = Integer.parseInt(pair[0]);
                int C = Integer.parseInt(pair[1]);

                A1 = new long[N+1];
                A2 = new long[N+1];

                Arrays.fill(A1, 0);
                Arrays.fill(A2, 0);

                for(int j=0; j<C; j++){
                    String [] command = consoleReader.readLine().split(" ");
                    boolean isAddition = Integer.parseInt(command[0])==0;

                    int p = Integer.parseInt(command[1]);
                    int q = Integer.parseInt(command[2]);

                    if(isAddition){
                        int v = Integer.parseInt(command[3]);
                        update(p, q, v);
                    }
                    else{
                        System.out.println(query(p,q));
                    }
                }

            }

        }
        catch(Exception exc){

        }
    }

    private static long query(long [] array, int q){
        long sum = 0;
        for (; q>0; q -= (q&(-q))) sum += array[q];
        return sum;
    }

    private static long query(int p, int q) {
        return query(q) - query(p - 1);
    }

    private static long query(int q) {
        return query(A1, q) * q - query(A2, q);
    }

    private static void update(long [] array, int p, int v) {
        for (; p <= N; p += (p&(-p))) array[p] += v;
    }

    private static void update(int p, int q, int v) {
        update(A1, p, v);
        update(A1, q + 1, -v);
        update(A2, p, v * (p - 1));
        update(A2, q + 1, -v * q);
    }
}
