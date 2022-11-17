package com.chainsys.novbatch;

import java.util.Scanner;

public class OddorEven {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter a Number");
		int n=s.nextInt();
		if(n%2==0)
		{
			System.out.println("odd number");
		}
		else
		{
			System.out.println("even Number");
		}

	}

}
