#include <stdio.h>
#include <iostream>
//#include <math.h>
#include <string>
#include <vector>
#include <bits/stdc++.h>

int main()
{
	int n,t;

	scanf("%d%d",&n,&t);
	int arr[504];
	//std :: vector<int> vec;
	int count=0;
	int flag=0;
	int temp[100];
	int k=1;
	while(n--)
	{
		while(t--)
		{	
			int a ,b;
			scanf("%d%d",&a,&b);
			//printf("%d %d\n",a,b);

			temp[k]=a;
			k++;
			temp[k]=b;
			k++;
			for (int i = a; i <= b; i++)
			{
				if(arr[i] == 1)
				{
					arr[i] = 9;
					flag++;
					 if(arr[i-1] == 9 && arr[i] ==9)
					{
						flag-=1;
						//printf("yes\n");
					}
				}
				
				else
					arr[i] = 1;
				count++;
			}

		//printf("%d\n",flag);	
		}
		int check = k/2;
		int i;
		int j=1;
		while(check--)
		{	
			
			int test=0;
			//printf("%d %d\n",temp[j],temp[j+1]);
			for(i = temp[j]; i <= temp[j+1] ; i++)
			{
				if(arr[i]==9)
				{
					test++;
				}
				
			}
			if(test==0)
			{
				flag++;
			}

			j=j+2;
			//printf("%d\n",j );
		}
		printf("%d\n",flag);
	
	}

}