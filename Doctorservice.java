package com.hospital.hospitalmanagement.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.hospitalmanagement.dao.Admindao;
import com.hospital.hospitalmanagement.dao.Doctordao;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.validation.Loginvalidation;
import com.hospital.hospitalmanagement.validation.Precriptionstatus;

@Service
public class Doctorservice {
	
	Doctordao doctordao=new Doctordao();

	Admindao admindao=new Admindao();
	
	public void doctorlogin(Admin admin,HttpSession session) throws Loginvalidation {
		doctordao.dlogin(admin, session);
		
	}
	public void doctoraddprofile(Doctor doctor) {
		doctordao.daddProfile(doctor);
	}
	public void listofdoctor(Patient patient,Model model) throws JsonProcessingException {
		

		doctordao.viewAppointmentList(patient,model);
		
	}
	public void listofapproval(Doctor doctor,Model model) throws JsonProcessingException {

		doctordao.listApproved(doctor, model);
	}
	public void historyofpatient(Model model,Doctor doctor) {
		List<Doctor> doctorprofile=doctordao.docprofileDetail(doctor);
		model.addAttribute("docprofile", doctorprofile);
		
	}
	public void billfilled(Admin admin) throws Precriptionstatus {
		admindao.patientBill(admin);
	}
	public void historyofdoctor(Model model,Doctor doctor) throws JsonProcessingException {

		admindao.doctorhistoryDetail(doctor, model);
	}
	public void doctorupdateprofile(Doctor doctor) {
		doctordao.dupdateProfile(doctor);
		
	}
	public void billcrdit(Admin admin,Model model) throws JsonProcessingException {

		doctordao.billOfCreation(admin,model);
		
	}
	

}
