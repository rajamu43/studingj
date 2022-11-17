package com.chainsys.novbatch.loops;

import java.util.Scanner;

public class Leapyear {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s=new Scanner(System.in);
		System.out.println("Enter the year");
		int year=s.nextInt();
		if(year%4==0)
		{
			System.out.println("This is leap Year");
		}
		else
		{
			System.out.println("This not leap year");
		}
	}

}
