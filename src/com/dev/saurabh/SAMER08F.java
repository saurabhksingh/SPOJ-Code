package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 5/24/14.
 */
public class SAMER08F {

    public static void main(String [] args){

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int squareSide = Integer.parseInt(br.readLine());
            while(squareSide != 0){

                if(squareSide == 1){
                    System.out.println(1);
                }
                else if(squareSide <= 0){
                    System.out.println(0);
                }
                else{
                    //formula is (m)(m+1)/2 (n)(n+1)/2 = m(m+1)(n)(n+1)/4
                    //here m == n

                    System.out.println(( (squareSide) * (squareSide+1) * (squareSide * 2 + 1))/6);
                }

                squareSide = Integer.parseInt(br.readLine());
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}

