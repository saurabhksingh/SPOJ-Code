package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ssaurab on 5/23/14.
 */
public class PUCMM210 {

    public static void main(String [] args){

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int totalNumberOfCases = Integer.parseInt(br.readLine());
            for(int i=0; i<totalNumberOfCases; i++){
                int upLimit = Integer.parseInt(br.readLine());
                int prevResult = 1;
                int prevSum = 1;
                for(int j=2; j<=upLimit; j++){

                }
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}