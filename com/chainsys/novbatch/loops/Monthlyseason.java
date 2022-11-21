package com.chainsys.novbatch.loops;

import java.util.Scanner;

public class Monthlyseason {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter a No.of Month");
		int month=s.nextInt();
		switch(month)
		{
		case 1:
			System.out.println("It is Spring Season");
			break;
		case 2:
			System.out.println("It is Spring Season");
			break;
		case 3:
			System.out.println("It is Spring Season");
			break;
		case 4:
			System.out.println("It is Summer Season");
			break;
		case 5:
			System.out.println("It is Summer Season");
			break;
		case 6:
			System.out.println("It is Summer Season");
			break;
		case 7:
			System.out.println("It is Monsoon Season");
			break;
		case 8:
			System.out.println("It is Monsoon Season");
			break;
		case 9:
			System.out.println("It is Monsoon Season");
			break;
		case 10:
			System.out.println("It is Winter Season");
			break;
		case 11:
			System.out.println("It is Winter Season");
			break;
		case 12:
			System.out.println("It is Winter Season");
			break;
		default:
				System.out.println("Invalid Input");
				break;
		}

	}

}
