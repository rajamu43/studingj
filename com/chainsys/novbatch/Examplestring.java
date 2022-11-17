package com.chainsys.novbatch;

public class Examplestring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       String name="Robertwilliam";
       String lastname="william";
       System.out.println(name.charAt(2));
       System.out.println(name.codePointAt(3));
       System.out.println(name.codePointBefore(2));
       System.out.println(name.codePointCount(0, 2));
       System.out.println(name.compareTo(lastname));
       System.out.println(name.compareToIgnoreCase(lastname));
       System.out.println(name.concat(lastname));
       System.out.println(name.contains(lastname));
       System.out.println(name.contentEquals(lastname));
       System.out.println(name.endsWith(lastname));
       System.out.println(name.equals(lastname));
       System.out.println(lastname.equalsIgnoreCase(name));
       System.out.println(name.hashCode());
       System.out.println(lastname.hashCode());
       System.out.println(name.indent(3));
		
	}

}
