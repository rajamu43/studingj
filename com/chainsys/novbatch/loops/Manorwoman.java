package com.chainsys.novbatch.loops;

import java.util.Scanner;

public class Manorwoman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter ur age:");
		int age=s.nextInt();
		System.out.println("Enter ur gender(M/F)");
		String gender=s.next();
	
		if(age<=20&&gender.equals("F"))
		{
			System.out.println(age+","+"'"+gender+"'");
			System.out.println("Girl");
		}
		else if(age>=20&&gender.equals("F"))
		{
			System.out.println(age+","+"'"+gender+"'");
			System.out.println("Women");
		}
		else if(age<=25&&gender.equals("M"))
		{
			System.out.println(age+","+"'"+gender+"'");
			System.out.println("Boy");
		}
		else if(age>=25&&gender.equals("M"))
		{
			System.out.println(age+","+"'"+gender+"'");
			System.out.println("Man");
		}
		else
		{
			System.out.println("invalid");
		}
	}

}
