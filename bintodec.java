package coursepractise;

import java.util.Scanner;

public class bintodec {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int bin = s.nextInt();
		int dec = 0;
		int sum = 0;
		int mult = 1;
		while (bin != 0){
			dec = bin % 10;
			sum = sum + (mult*dec);
			mult = mult*2;
			bin = bin/10;
		}
		System.out.println(sum);
	}
}
