package com.chainsys.novbatch;

import java.util.Scanner;

public class Examattendance1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		
		
		System.out.println("Less then attendance reason for medical cause:");
		String reason=s.next();
		/*System.out.println("Enter the no class attended");
		String no=s.next();*/
		if(reason.equals("yes"))
        {
        	System.out.println("Your submitted medical certificate and allowed in Exam");
        }
		else if(reason.equals("no"))
		{
			System.out.println("Your not allowed in exam");
		}
        else
        {
        	System.out.println("invalid input");
        	
        }

	}

}
