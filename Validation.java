package com.hospital.hospitalmanagement.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ui.Model;

public class Validation {
	
	public boolean namevalidation(String name,Model model) 
	{
		String regex="[a-zA-Z]+";
		Pattern pat1=Pattern.compile(regex);
		Matcher mat1=pat1.matcher(name);
		boolean mat2=mat1.matches();
		System.out.println(mat2+"value");
		if(mat2) {
			
		return mat2;
		}
		else
		{
			String message="give name characters only";
			model.addAttribute("errorMessage4", message);
			return false;
		}
	}
	public boolean pwdvalidation(String name,Model model)
	{
		String regex="[a-zA-Z0-9]+";
		Pattern pat1=Pattern.compile(regex);
		Matcher mat1=pat1.matcher(name);
		boolean mat2=mat1.matches();
		if(mat2) {
		return mat2;
		}
		else
		{
			String passwordValid="give password characters&numbers only.not specify any spcial character";
			model.addAttribute("errorMessage5", passwordValid);
			return false;
		}
		
	}

	public boolean mnovalidation(long mno,Model model)
	{
		
		String regex=Long.toString(mno);
		Pattern pat1=Pattern.compile("^\\d{10}$");
		Matcher mat1=pat1.matcher(regex);
		boolean mat2=mat1.matches();
		if(mat2) {
		return mat2;
		}
		else {
			String mobileValid="give 10 digit numbers only";
			model.addAttribute("errorMessage7", mobileValid);
			return false;
		}
		
	}
	public boolean idvalidation1(int no,Model model)
	{
		if(no>0)
		{
			return true;

		}
		else {
			String message="Give positive number only";
			model.addAttribute("errorMessage8", message);
			return false;
		}
	}
	public boolean idvalidation(Integer no,Model model) 
	{
		
		String num=Integer.toString(no);
		Pattern pat=Pattern.compile("^\\d{4}$");
		Matcher mat=pat.matcher(num);
		boolean matches=mat.matches();
		if(matches)
		{
			return true;
		}
		else {
			String message="give 4 digit positive numbers only";
			model.addAttribute("errorMessage9", message);
			return false;
		}
	}
	public boolean gendervalidation(String gen,Model model)
	{
		String regex="^male$|^female$";
		Pattern pat1=Pattern.compile(regex);
		Matcher mat1=pat1.matcher(gen);
		boolean mat2=mat1.matches();
		if(mat2) {
		return mat2;
		}
		else {
			String message="give input as male or female only";
			model.addAttribute("errorMessage1", message);
			return false;
		}
		
	}
	public boolean agevalidation(int no,Model model)
	{
		if(no>0)
		{
			return true;

		}
		else {
			String message="give age 3 digit positive nos only";
			model.addAttribute("errorMessage2", message);
			return false;
		}
	}
	public boolean mailvalidation(String mail,Model model)
	{
		final String EMAIL_PATTERN="^(.+)@(.+)$";
		Pattern pat=Pattern.compile(EMAIL_PATTERN);
		Matcher mat=pat.matcher(mail);
		boolean matches=mat.matches();
		if(matches)
		{
		return matches;
		}
		else {
			String message="give mailid is @ symbol";
			model.addAttribute("errorMessage10", message);
			return false;
		}
	}
	public boolean mnovalidation(String mno) throws Mnovalidation
	{
		String regex="^\\d{10}$";
		Pattern pat1=Pattern.compile(regex);
		Matcher mat1=pat1.matcher(mno);
		boolean mat2=mat1.matches();
		if(mat2) {
		return mat2;
		}
		else {
			throw new Mnovalidation();
		}
		
	}
	public boolean addressvalidation(String address,Model model)
	{
		String regex="^[0-9a-zA-Z\\s,-]+$";
		Pattern pat1=Pattern.compile(regex);
		Matcher mat1=pat1.matcher(address);
		boolean mat2=mat1.matches();
		if(mat2)
		{
			return true;
		}
		else
		{
			String message="not specify any special character.ex(@#)";
			model.addAttribute("errorMessage3", message);
			return false;
		}
	}
	public boolean paymentvalidation(String mno) throws Payvalidation
	{
		String regex="^\\d{10}$|^\\d{16}$";
		Pattern pat1=Pattern.compile(regex);
		Matcher mat1=pat1.matcher(mno);
		boolean mat2=mat1.matches();
		if(mat2) {
		return mat2;
		}
		else {
			throw new Payvalidation();
		}
		
	}
	

}
