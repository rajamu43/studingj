package com.chainsys.novbatch.array;

public class Bigandsmallarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numbers[]= {43,22,65,44,12,78};
		int largest=numbers[0];
		int small=numbers[0];
		
		for(int i=0;i<numbers.length;i++)
		{
			if(numbers[i]>largest)
			{
				largest=numbers[i];
				
			}
			else if(numbers[i]<small)
			{
				small=numbers[i];
			}
			
	   }
		System.out.println(largest);
		System.out.println(small);

	}

}
