package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaurab on 3/18/14.
 */
public class CANDY3 {

    public static void main(String [] args){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int testCases = Integer.valueOf(br.readLine());
            br.readLine();
            for(int i=0; i<testCases; i++){

                int numberOfStudents = Integer.valueOf(br.readLine());
                int [] candies = new int[numberOfStudents];
                long sum = 0;
                for(int j=0; j<numberOfStudents; j++){
                    candies[i] = Integer.valueOf(br.readLine())%numberOfStudents;
                    sum += candies[i];
                }

                if(sum%numberOfStudents == 0){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }

                br.readLine();
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
}
