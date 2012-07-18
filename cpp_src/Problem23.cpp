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
#include <algorithm>

using namespace std;


int main(int argc, char * argv[])
{
	/**
	*solution is in formula : we will make use of Herone-type formula @ http://en.wikipedia.org/wiki/Tetrahedron#Volume

	Input : AB(W), AC(V), AD(u), BC(U), BD(v), CD(w).

	*/
	int numberOfTestCases;
	cin>>numberOfTestCases;
	vector<double> result;
	for(int i=0 ;i<numberOfTestCases; i++)
	{
		int u,v,w, U,V,W;

		cin>> W;
		cin>> V;
		cin>> u;
		cin>> U;
		cin>> v;
		cin>> w;
		double X = (w-U+v)*(U+v+w);
		double x = (U-v+w)*(v-w+U);
		double Y = (u-V+w)*(V+w+u);
		double y = (V-w+u)*(w-u+V);
		double Z = (v-W+u)*(W+u+v);
		double z = (W-u+v)*(u-v+W);

		double a = sqrt(x)*sqrt(Y)*sqrt(Z);
		double b = sqrt(y)*sqrt(Z)*sqrt(X);
		double c = sqrt(z)*sqrt(X)*sqrt(Y);
		double d = sqrt(x)*sqrt(y)*sqrt(z);
		double nominator = sqrt(-a+b+c+d)*sqrt(a-b+c+d)*sqrt(a+b-c+d)*sqrt(a+b+c-d);
		//double nominator = sqrt(s_nominator);
		double volume = (nominator/192);
		volume = volume/u;
		volume = volume/v;
		volume = volume/w;
		
		/*
		u = u*u; v = v*v; w = w*w;
		U = U*U; V = V*V; W = W*W;
		double factor1 = 4*u*v*w;
		double factor2 = u * (v+w-U)*(v+w-U);
		double factor3 = v * (w+u-V)*(w+u-V);
		double factor4 = w * (u+v-W)*(u+v-W);
		double factor5 = (v+w-U)*(w+u-V)*(u+v-W);
		double volume = sqrt(factor1 - factor2 -factor3 -factor4 + factor5)/12;
		*/
		result.push_back(volume);

	}
	cout<<endl;
	for(int i=0; i<result.size(); i++)
	{
		printf("%.4f\n", result[i]);
	}
	return 0;
}

