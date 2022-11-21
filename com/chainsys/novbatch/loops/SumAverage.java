package com.chainsys.novbatch.loops;

import java.util.Scanner;

public class SumAverage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter a Number");                              
		int number=s.nextInt();
	    int total=0,average;
		for(int i=1;i<=number;i++)
		{
			total=total+i;
			
			
		}
		
		average=total/number;
		System.out.println("Total Num is:"+average);
        
	}

}
