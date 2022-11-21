package com.chainsys.novbatch.array;

import java.util.Scanner;

public class Reversearray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s=new Scanner(System.in);
		System.out.println("Enter the Number:");
		int[] number=new int[10];
		for(int i=0;i<number.length;i++)
		{
			number[i]=s.nextInt();
			
		}
		int number1[]=new int[number.length];
		System.out.println("The array of elements");
		for(int i=0;i<number.length;i++)
		{
			number1[i]=number[i];
			System.out.print(number[i]+" ");
		}
		System.out.println();
		System.out.println("The Reverse order of elements");
		for(int i=number1.length-1;i>=0;i--)
		{
			System.out.print(number1[i]+" ");
		}	
	}
}
