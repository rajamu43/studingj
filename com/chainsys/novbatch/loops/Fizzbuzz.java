package com.chainsys.novbatch.loops;

import java.util.Scanner;

public class Fizzbuzz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter a Number");
		int number=s.nextInt();
		if(number%3==0 &&number%5==0)
		{
			System.out.println("Fizzbuzz");
		}
		else if(number%3==0)
		{
			System.out.println("buzz");
		}
		else if(number%5==0)
		{
			System.out.println("Fizz");
		}
		else
		{
			System.out.println("invalid");
		}

	}

}
