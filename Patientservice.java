package com.hospital.hospitalmanagement.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.hospitalmanagement.dao.Doctordao;
import com.hospital.hospitalmanagement.dao.Patientdao;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.validation.Appointmentreqvalidation;
import com.hospital.hospitalmanagement.validation.Loginvalidation;

@Service
public class Patientservice {
	
	
	Patientdao patientdao=new Patientdao();
	
	Doctordao doctordao=new Doctordao();
	
	public void patientlogin(Admin admin,HttpSession session) throws Loginvalidation {
		patientdao.plogin(admin,session);
		
	}
	public void patientAddprofile(Patient patient) {
		patientdao.addProfile(patient);
	}
	public void patientUpdateprofile(Patient patient) {
		patientdao.updateProfile(patient);
	}
	public void patientRemoveprofile(Patient patient) {
		patientdao.removeProfile(patient);
		
	}
	public void doctordetail(Model model) {
		List<Doctor> doctors=patientdao.listofDoctors();
		model.addAttribute("doclist", doctors);
		
	}

	public void paymentPay(Admin admin,HttpSession session) {
		patientdao.paymentAdd(admin,session);
	}

	public void mybillcrdit(Admin admin,Model model) throws JsonProcessingException {

		patientdao.mylistofBills(admin, model);
		
	}
	public void doctorSpecial(Doctor doctor,Model model) {
		List<Doctor> speciality=patientdao.specification(doctor);
		model.addAttribute("specialization", speciality);		
	}

}
