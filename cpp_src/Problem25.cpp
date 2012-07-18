/**
Given two vessels, one of which can accommodate a litres of water and the other - b litres of water, determine the number of steps required to obtain exactly c litres of water in one of the vessels.

At the beginning both vessels are empty. The following operations are counted as 'steps':

emptying a vessel,
filling a vessel,
pouring water from one vessel to the other, without spilling, until one of the vessels is either full or empty.
*/
#include <iostream>
#include <stdio.h>
#include <string>
#include <math.h>
#include <iomanip>
#include <vector>
#include <algorithm>

using namespace std;

int gcd(int first,int second) {
    return second==0?first:gcd(second,first%second);
}
int getPourSteps(int from, int to, int target)
{
	int result = 0,tmp_from = 0,tmp_to = 0;
    while(true) {
        if (tmp_from==target || tmp_to==target) return result;
        if (tmp_to==to) tmp_to = 0;
        else if (tmp_from==0) tmp_from = from;
        else {
            int delta = min(to-tmp_to,tmp_from);
            tmp_from -= delta; tmp_to += delta;
        }
        result++;
    }
}
int getNumberOfSteps(int first, int second, int target)
{
	int result = -1;
	if(first == target || second == target)
	{
		result = 1;
	}
	else if(target == 0)
	{
		result = 0;
	}
	else if(!(target > max(first,second)) && target%gcd(first,second) == 0)
	{
		result = min(getPourSteps(first, second, target), getPourSteps(second, first, target));
	}

	return result;
}
int main(int argc, char* argv[])
{
	int numberOfTestCases;
	cin>>numberOfTestCases;
	vector<int> result;
	for(int i=0 ;i<numberOfTestCases; i++)
	{
		int first,second,target;
		cin>>first;
		cin>>second;
		cin>>target;

		int steps = getNumberOfSteps(first, second, target);
		result.push_back(steps);
	}
	cout<<endl;
	for(int i=0; i<result.size(); i++)
	{
		cout<<result[i]<<endl;
	}
	return 0;
}

