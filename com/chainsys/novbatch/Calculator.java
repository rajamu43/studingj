package com.chainsys.novbatch;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int n3;
		System.out.println("Enter  1st no:");
		int n1=s.nextInt();
		System.out.println("Choose a operator: +,-,*,/");
        char operator=s.next().charAt(0);
		System.out.println("Enter 2nd no");
		int n2=s.nextInt();
        
        if(operator == '+')
        {
        	System.out.println("The add is:"+(n1+n2));
        }
        else if(operator =='-')
        {
        	System.out.println("The subtraction is:"+(n1-n2));
        }
        else if(operator =='*')
        {
        	System.out.println("The Multiplication is:"+(n1*n2));
        }
        else if(operator =='/')
        {
        	System.out.println("The division is:"+(n1/n2));
        }
        else
        {
        System.out.println("Invalid input");
        }
	}

}
