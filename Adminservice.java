package com.hospital.hospitalmanagement.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.hospitalmanagement.dao.Admindao;
import com.hospital.hospitalmanagement.dao.Doctordao;
import com.hospital.hospitalmanagement.dao.Patientdao;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.validation.Appointmentreqvalidation;
import com.hospital.hospitalmanagement.validation.Invalidname;
import com.hospital.hospitalmanagement.validation.Loginvalidation;
import com.hospital.hospitalmanagement.validation.Medicinevalidation;
@Service
public class Adminservice {
	
	
	Patientdao patientdao=new Patientdao();
	
	Doctordao doctordao=new Doctordao();
	
	Admindao admindao=new Admindao();
	
	public void register(Admin admin,HttpSession session) throws Invalidname {
		admindao.registration(admin, session);
		
	}
	public void adminlog(Admin admin,HttpSession session) throws Loginvalidation {
		admindao.adminLogin(admin, session);
		
	}
	public void profileofdoctor(Admin admin) {
		admindao.adminAddprofile(admin);
		
	}
	public void listprecription(Model model) throws JsonProcessingException {

		admindao.listOfPrescription(model);
		
	}
	public void payable(Model model) throws JsonProcessingException {

		admindao.listOfPayment(model);
	}
	public void listofphistory(Doctor doctor,Model model) throws JsonProcessingException {

		admindao.historydetail(doctor,model);
	}
	public void listofpdate(Doctor doctor,Model model) {
		List<Doctor> patientdate=admindao.historyOfpdate(doctor);
		model.addAttribute("phistory", patientdate);
		
	}
	public void updateprofile(Doctor doctor) {
		doctordao.dupdateProfile(doctor);
	}
	public void precriptionadd(Doctor doctor,Admin admin) throws Appointmentreqvalidation, Medicinevalidation {
		doctordao.addPrecription(doctor);
        doctordao.history(doctor);
        doctordao.historyofpatient(doctor,admin);
	}
	
	public void viewBilllist(Admin admin,Model model) throws JsonProcessingException {

		doctordao.listOfBill(admin, model);
	}

}
