package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RobotGRI
{

    public static void main(String [] args)
    {
         try
         {
             int max_number = (1<<31) -1;
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             int gridSize = Integer.parseInt(consoleReader.readLine().trim());
             boolean rowNotMarked = true;
             boolean colNotMarked = true;

             if(gridSize <= 0)
             {
                 System.out.println(0);
                 return;
             }
             int [][] matrix = new int [gridSize][gridSize];

             for(int i=0; i<gridSize; i++)
             {
                 String line = consoleReader.readLine().trim();
                 for(int j=0; j<line.length(); j++)
                 {
                     matrix[i][j] = line.charAt(j)!='.'?-1:0;
                 }
             }
             if(matrix[gridSize-1][gridSize-1] == -1)
             {
                 System.out.println(0);
                 return;
             }
             for(int i=gridSize-1; i>=0; i--)
             {
                 for(int j=gridSize-1; j>=0; j--)
                 {
                     if(i==gridSize-1 || j==gridSize-1)
                     {
                         if(matrix[i][j] == -1)
                         {
                             if(j == gridSize-1 && rowNotMarked)
                             {
                                 int k = i;
                                 while(k>0)
                                 {
                                     matrix[k--][j] = -1;
                                 }

                                 rowNotMarked = false;
                             }
                             else if(i == gridSize-1 && colNotMarked)
                             {
                                 int k = j;
                                 while(k>0)
                                 {
                                     matrix[i][k--] = -1;
                                 }

                                 colNotMarked = false;
                             }
                         }
                         else
                         {
                             matrix[i][j] = 1;
                         }
                     }
                     else
                     {
                         if(matrix[i][j] != -1)
                         {
                             int rightStep = matrix[i][j+1]==-1?0:matrix[i][j+1];
                             int bottomStep = matrix[i+1][j]==-1?0:matrix[i+1][j];
                             if(rightStep+bottomStep <= 0)
                             {
                                 matrix[i][j] = -1;
                             }
                             else
                             {
                                 matrix[i][j] = (rightStep+bottomStep)%max_number;
                             }
                         }
                     }
                 }
             }

             System.out.println(matrix[0][0]);
         }
         catch(Exception exc)
         {
             exc.printStackTrace();
         }
    }
}
