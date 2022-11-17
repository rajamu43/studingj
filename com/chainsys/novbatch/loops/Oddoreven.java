package com.chainsys.novbatch.loops;

import java.util.Scanner;

public class Oddoreven {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the range of no");
		int range=s.nextInt();
		for(int i=1;i<=range;i++)
		{
			if(i%2==0)
			{
				System.out.println(i+" is odd number");
			}
			else
			{
				System.out.println(i+" is even number");
			}
			
		}

	}

}
