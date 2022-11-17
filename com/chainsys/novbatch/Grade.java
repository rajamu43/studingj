package com.chainsys.novbatch;

import java.util.Scanner;

public class Grade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s=new Scanner(System.in);
		System.out.println("Enter ur marks");
		int m1=s.nextInt();
		int m2=s.nextInt();
		int m3=s.nextInt();
		int m4=s.nextInt();
		int m5=s.nextInt();
		int grade=m1+m2+m3+m4+m5/100;
		//System.out.println("Enter a grade");
		//int grade=s.nextInt();
		if(grade>=80)
		{
			System.out.println("A grade");
		}
		else if(grade>=60&&grade<=80)
		{
			System.out.println("B grade");
		}
		else if(grade>=50&&grade<=60)
		{
			System.out.println("C grade");
		}
		else if(grade>=45&&grade<=50)
		{
			System.out.println("D grade");
		}
		else if(grade>=25&&grade<=45)
		{
			System.out.println("E grade");
		}
		else
		{
			System.out.println("F grade");
		}
		
	}

}
