package com.hospital.hospitalmanagement.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.validation.Invalidname;
import com.hospital.hospitalmanagement.validation.Loginvalidation;
import com.hospital.hospitalmanagement.validation.MailidValidation1;
import com.hospital.hospitalmanagement.validation.Patientname;
import com.hospital.hospitalmanagement.validation.Payment;
import com.hospital.hospitalmanagement.validation.Precriptionstatus;
import com.hospital.hospitalmanagement.validation.Transactionpin;

public interface AdminInterface {
	
	public void registration(Admin admin,HttpSession session)throws Invalidname;
	public void adminAddprofile(Admin admin);
	public void adminUpdateprofile(Admin admin);
	public void listOfPrescription(Model model)throws JsonProcessingException ;
	public void patientBill(Admin admin) throws Precriptionstatus;
	public void paycheck(Admin admin) throws Transactionpin,Payment;
	public void billName(Admin admin,Model model) throws Patientname;
	public void historydetail(Doctor doctor,Model model)throws JsonProcessingException;
	public void doctorhistoryDetail(Doctor doctor,Model model)throws JsonProcessingException ;
	public Integer doctorCount() ;
	public Integer patientCount();
	public Integer appointmentCount();
	public Boolean adminLogin(Admin admin,HttpSession session);
	public Boolean mailValidation(Admin admin,Model model);
	public List<Patient> profileDetail(Patient patient,Model model);
	public List<Doctor> mymedicineDetail(Doctor doctor,Model model);

}
