package com.chainsys.novbatch;

import java.util.Scanner;

public class Checksqure {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int squre;
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the lenth value:");
		int lenth=s.nextInt();
	    System.out.println("Enter the breadth value:");
		int breadth=s.nextInt();
		System.out.println("Enter ur choice;");
		String choice=s.next();
		if(choice.equals("square"))
		{
			squre=lenth*breadth;
			System.out.println("This squre is:"+squre);
		}
		else
		{		
			System.out.println("This is not squre");
		}

	}

}
