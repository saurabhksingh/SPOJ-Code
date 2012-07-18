/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/12/12
 * Time: 12:03 AM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */

#include <iostream>
#include <stdio.h>
#include <string>
#include <math.h>
#include <iomanip>
#include <vector>

using namespace std;

int main(int argc, char* argv[])
{
	vector<double> result;
    int numberOfTestCases;
	cin>>numberOfTestCases;

   // cout<<endl;
	double a,b,c, Pa,Pb,Pc;
    for(int i=0; i<numberOfTestCases; i++)
    {
        cin>>a>>Pa>>Pb>>Pc;
		//cout<<(a+Pa);
		double area = (1.5 * a * Pa);
		double temp = (area/3.0)*2.0;
        double b = temp/Pb;  //System.out.println(b);
        double c = temp/Pc;
        double a2 = a*a;
        double b2 = b*b;
        double c2 = c*c;
		double area2 = area*area;
		result.push_back(area);
		double distance = (sqrt(((0.5625*a2*b2*c2))/area2 - (a2 + b2 + c2))/3.0)*2.0;
		if(distance != distance)
		{
			//case of NaN
			distance = 0.000;
		}
		result.push_back(distance);
		//cout <<setprecision(20)<<"area: "<<area<<" ,distance: "<<distance<<endl;
    }
	cout<<endl;
	for(int i=0; i<result.size()-1; i=i+2)
	{
		printf("%.3f %.3f\n",result[i], result[i+1]);
	}

	return 0;
}
