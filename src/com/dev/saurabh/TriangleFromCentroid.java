package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/18/12
 * Time: 3:22 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class TriangleFromCentroid
{
    /**
     * Given the length of side a of a triangle and the distances from the centroid (the point of concurrence
     * of the medians - red in the picture) to all sides: a, b and c, calculate this triangle's area and the
     * distance (blue line) from the orthocenter (the point of concurrence of the heights - green in the picture)
     * to the centroid.
     */

    public static void main(String [] args)
    {
        try
        {
          //refer to http://en.wikipedia.org/wiki/Area_of_triangle
          //area of triangle will be (1.5 * a * Pa), Pa is distance of centroid from side a

            //refer to http://mathforum.org/kb/thread.jspa?forumID=125&threadID=1106101&messageID=3617504
            //for distance calculation

            BufferedReader consoleReader= new BufferedReader(new InputStreamReader(System.in));
            int numberOfTestCases = Integer.parseInt(consoleReader.readLine());
            StringBuilder out = new StringBuilder();

            for(int i=0; i<numberOfTestCases; i++)
            {
                String input = consoleReader.readLine();
                String [] in = input.split(" ");
                double a  = Double.parseDouble(in[0]);
                double Pa = Double.parseDouble(in[1]);
                double Pb = Double.parseDouble(in[2]);
                double Pc = Double.parseDouble(in[3]);
                double b,c,a2,b2,c2,distance;
                double area = (1.5 * a * Pa);
               // System.out.println(area);
                out.append(String.format("%.3f", area)).append(" ");
                if(Pa == Pb && Pb == Pc)
                {
                    b=a;
                    c=a;
                    a2=b2=c2=Math.pow(a,2);
                }
                else
                {
                    double temp = (area/3)*2;
                    b = temp/Pb;  //System.out.println(b);
                    c = temp/Pc;
                    a2 = Math.pow(a,2);
                    b2 = Math.pow(b,2);
                    c2 = Math.pow(c,2);
                }

                double area2 = Math.pow(area,2);
                distance = (Math.sqrt(((0.5625*a2*b2*c2))/area2 - (a2 + b2 + c2))/3)*2;
                if(Double.isNaN(distance))
                    distance = 0.000;

                out.append(String.format("%.3f", distance)).append("\n");
            }
            System.out.println();
            System.out.print(out);
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
