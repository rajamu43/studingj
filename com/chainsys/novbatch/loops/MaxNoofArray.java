package com.chainsys.novbatch.loops;

public class MaxNoofArray {

	public static void main(String[] args) {

		int[] number= {23,2,5,50};
		int large,large1,temp;
		large=number[0];
		large1=number[1];
		if(large<large1)
		{
			temp=large;
			large=large1;
			large1=temp;
		}
		
		for(int i=2;i<number.length;i++)
		{
			if(number[i]>large)
			{
				large1=large;
				large=number[i];
				
			}
			else if(number[i]>large1&&number[i]!=large)
			{
				large1=number[i];
			}
		}
		
		System.out.println(large);
        System.out.println(large1);
	}

}
