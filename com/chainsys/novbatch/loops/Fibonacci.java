package com.chainsys.novbatch.loops;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int number1=0,number2=1,sum;
		System.out.println("Enter the Number");
		int number=s.nextInt();
		System.out.print(number1);
		System.out.print(" "+number2);
		for (int i=1;i<=number;i++)
		{
			
			sum=number1+number2;
			
			number1=number2;
			number2=sum;
			System.out.print(" "+sum);
		}
        
	}

}
