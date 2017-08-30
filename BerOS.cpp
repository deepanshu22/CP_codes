#include <stdio.h>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <bits/stdc++.h>
#include <set>

using namespace std;

int main()
{
	//std::vector<char> v;
	char arr[10005];
	char temp[10004];
	scanf("%s",arr);

	int len=0;
	for (int i = 0; i < 101; ++i)
	{
		if(arr[i]=='\0')
		{
			break;
		}
		len++;
	}
	int count=0;
	int j=0;

	for (int i = 0; i < len; ++i)
	{
		if(arr[i] == '/')
		{
			count++;
			//printf("%d\n",count );
			if (count == len)
			{	
				//printf("yes\n");
				temp[j] = arr[i];
			}			
			else if(arr[i] != arr[i+1] && i!= len-1)
			{
				temp[j] = arr[i];
				j++;
			}
			
				
		}

		else
		{	
			temp[j] = arr[i];
			j++;
			// count=0;
		}
		//printf("%d %d\n",count,len);
	}
	if (j==0)
	{
		cout << temp[0] << endl;
	}
	else
	{
	for (int i = 0; i < j; ++i)
	{
		cout << temp[i];
	}
	printf("\n");
	}
}