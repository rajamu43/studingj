package com.chainsys.novbatch.day;

import java.util.Scanner;

public class Gst {

	public static void main(String[] args) {

		Scanner s=new Scanner(System.in);
		System.out.println("Enter user name");
		String name=s.next();
		if(name.length()>3)
		{
			System.out.println("Products the category");
			System.out.println("1.Plastic");
			System.out.println("2.Steel");
			System.out.println("3.mobiles");
			System.out.println("4.Textile");
			System.out.println("5.food");
			System.out.println("Select the category");
			int choose=s.nextInt();
			switch(choose)
			{
			case 1:
				System.out.println("Enter the quantity of plastic");
				int quantity=s.nextInt();
				if(quantity>0)
				{
					System.out.println("For this category GST 12%");
                    System.out.println("Enter amount is:");
                    int amount=s.nextInt();
					System.out.println("Product amount is"+amount);
					System.out.println("Product qty is:"+quantity);
                   int bill=quantity*amount;
					int gst=bill+(bill*12)/100;
					
					System.out.println("ur total amont is:"+gst);
					if(gst>=1000)
					{
						System.out.println("ur valid offer");
						int offer=gst-50;
						System.out.println("Offer amount is:Rs.50");
						System.out.println("ur payable amount is:"+offer);
					}
					else
					{
						System.out.println("Not valid for offer");
						System.out.println("ur payable amount is:"+gst);
					}
					
				}
				else
				{
					System.out.println("give valid quantity");
				}
				break;
			case 2:
				System.out.println("Enter the quantity of steel");
				int quantity1=s.nextInt();
				if(quantity1>0)
				{
					System.out.println("For this category GST 13%");
                    System.out.println("Enter amount is:");
                    int amount=s.nextInt();
					System.out.println("Product amount is"+amount);
					System.out.println("Product qty is:"+quantity1);
					 int bill=quantity1*amount;
						int gst=bill+(bill*13)/100;
					System.out.println("ur payable amont is:"+gst);
					if(gst>=1000)
					{
						System.out.println("ur valid offer");
						int offer=gst-50;
						System.out.println("Offer amount is:Rs.50");
						System.out.println("ur payable amount is:"+offer);
					}
					else
					{
						System.out.println("Not valid for offer");
						System.out.println("ur payable amount is:"+gst);
					}
					
				}
				else
				{
					System.out.println("give valid quantity");
				}
				break;
			case 3:
				System.out.println("Enter the quantity of mobiles");
				int quantity2=s.nextInt();
				if(quantity2>0)
				{
					System.out.println("For this category GST 15%");
                    System.out.println("Enter amount is:");
	                    int amount=s.nextInt();
						System.out.println("Product amount is"+amount);
						System.out.println("Product qty is:"+quantity2);
						 int bill=quantity2*amount;
							int gst=bill+(bill*15)/100;

					
					System.out.println("ur payable amont is:"+gst);
					if(gst>=1000)
					{
						System.out.println("ur valid offer");
						int offer=gst-50;
						System.out.println("Offer amount is:Rs.50");
						System.out.println("ur payable amount is:"+offer);
					}
					else
					{
						System.out.println("Not valid for offer");
						System.out.println("ur payable amount is:"+gst);
					}
				}
				else
				{
					System.out.println("give valid quantity");
				}
				break;
			case 4:
				System.out.println("Enter the quantity of textile");
				int quantity3=s.nextInt();
				if(quantity3>0)
				{
					System.out.println("For this category GST 20%");

				    System.out.println("Enter amount is:");
                    int amount=s.nextInt();
					System.out.println("Product amount is"+amount);
					System.out.println("Product qty is:"+quantity3);
					 int bill=quantity3*amount;
						int gst=bill+(bill*20)/100;

					System.out.println("ur payable amont is:"+gst);
					if(gst>=1000)
					{
						System.out.println("ur valid offer");
						int offer=gst-50;
						System.out.println("Offer amount is:Rs.50");
						System.out.println("ur payable amount is:"+offer);
					}
					else
					{
						System.out.println("Not valid for offer");
						System.out.println("ur payable amount is:"+gst);
					}
				}
				else
				{
					System.out.println("give valid quantity");
				}
				break;
			case 5:
				System.out.println("Enter the quantity of Food");
				int quantity4=s.nextInt();
				if(quantity4>0)
				{
					System.out.println("For this category GST 16%");
					 System.out.println("Enter amount is:");
	                    int amount=s.nextInt();
						System.out.println("Product amount is"+amount);
						System.out.println("Product qty is:"+quantity4);
						 int bill=quantity4*amount;
							int gst=bill+(bill*20)/100;

					System.out.println("ur payable amont is:"+gst);
					if(gst>=500)
					{
						System.out.println("ur valid offer");
						int offer=gst-50;
						System.out.println("Offer amount is:Rs.50");
						System.out.println("ur payable amount is:"+offer);
					}
					else
					{
						System.out.println("Not valid for offer");
						System.out.println("ur payable amount is:"+gst);
					}
					
				}
				else
				{
					System.out.println("give valid quantity");
				}
				break;
			default:
				System.out.println("Choose valid catory");
			}
			
		}
		else
		{
			System.out.println("give valid amount");
		}
		
  }

}
