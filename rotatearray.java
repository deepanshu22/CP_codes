package jun18;

import java.util.Scanner;

public class rotatearray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = arrayutils.takeinput();
		arrayutils.display(arr);
		Scanner s = new Scanner(System.in);
		
		int r = s.nextInt();
		int left1 =0;
		int right1 = arr.length-1-r;
		int left2 =arr.length-r;
		int right2 = arr.length -1;
		
		reverse(arr,left1,right1);
		reverse(arr,left2,right2);
		reverse(arr,0,arr.length-1);
		arrayutils.display(arr);
		
		
	}
	private static void reverse(int[]arr,int left,int right)
	{
		 while(left<=right)
		 {
			 arrayutils.swap(arr, left, right);
			// int temp=arr[left];
			// arr[left]= arr[right];
			 //arr[right]=temp;
			 left++;
			 right--;
		 }
		
	}

}
