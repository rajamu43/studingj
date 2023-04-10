package com.hospital.hospitalmanagement.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.validation.Appointmentreqvalidation;
import com.hospital.hospitalmanagement.validation.Loginvalidation;

public interface PatientInterface {
	
	public Boolean plogin(Admin admin,HttpSession session);
	public Boolean addProfile(Patient patient);
	public void updateProfile(Patient patient);
	public void removeProfile(Patient patient);
	public List<Doctor> listofDoctors();
	public void doctorList(Model model)throws JsonProcessingException;
	
	//public List<Admin> listofbill(Admin admin);
	public List<Admin> mylistofBills(Admin admin,Model model)throws JsonProcessingException;
	public void paymentAdd(Admin admin,HttpSession session);

}
