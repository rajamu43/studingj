package com.chainsys.novbatch;

import java.util.Scanner;

public class Scorecard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Scanner s=new Scanner(System.in);
		int player1=42,player2=38,player3=60,teamA;
		int playerA=19,playerB=80,playerC=28,teamB;
	
	    teamA=player1+player2+player3;
	    teamB=playerA+playerB+playerC;
	    
	    System.out.println("TeamA"+"          "+"TeamB");
	    System.out.println("PlayerA ->"+player1+" "+"PlayerA ->"+playerA);
	    System.out.println("PlayerB ->"+player2+" "+"PlayerB ->"+playerB);
	    System.out.println("PlayerC ->"+player3+" "+"PlayerC ->"+playerC);
	    System.out.println("Score  ->"+teamA+"  "+"Score  ->"+teamB);
	    if(teamA>teamB)
	    {
	    	System.out.println("TeamA win against TeamB");
	    }
	    else if(teamB>teamA)
	    {
	    	System.out.println("TeamB win against TeamA");
	    }
	    else
	    {
	    	System.out.println("Match Draw");
	    }

	}

}
