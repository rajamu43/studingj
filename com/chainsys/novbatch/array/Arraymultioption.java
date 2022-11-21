package com.chainsys.novbatch.array;

import java.util.Scanner;

public class Arraymultioption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int[] number=new int[20];
		for(int i=0;i<number.length;i++)
		{
			number[i]=s.nextInt();
			
		}
		System.out.println("No of odd Numbers");
		for (int i=0;i<number.length;i++)
		{
			if(number[i]%2!=0)
			{
				System.out.println(number[i]);
			}
		}
		System.out.println("No of even Numbers");
		for (int i=0;i<number.length;i++)
		{
			if(number[i]%2==0)
			{
				System.out.println(number[i]);
			}
		}
		System.out.println("No of Positive Numbers");
		for (int i=0;i<number.length;i++)
		{
			if(number[i]>0)
			{
				System.out.println(number[i]);
			}
		}
		System.out.println("No of Negative Numbers");
		for (int i=0;i<number.length;i++)
		{
			if(number[i]<0)
			{
				System.out.println(number[i]);
			}
		}
		System.out.println("No of 0 Numbers");
		for (int i=0;i<number.length;i++)
		{
			if(number[i]==0)
			{
				System.out.println(number[i]);
			}
		}
	}

}
