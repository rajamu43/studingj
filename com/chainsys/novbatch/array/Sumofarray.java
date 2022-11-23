package com.chainsys.novbatch.array;

public class Sumofarray {

	public static void main(String[] args) {

		int number[]= {5,4,12,20,10};
		int sum=0;
		int sum1 = 1;
		for(int i=0;i<number.length;i++)
		{
			sum=sum+number[i];
			sum1=sum1*number[i];
		}
		System.out.println("The sum of No is:"+sum);
		System.out.println("The product of No is:"+sum1);


	}

}
