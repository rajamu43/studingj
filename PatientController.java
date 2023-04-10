package com.hospital.hospitalmanagement.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.hospitalmanagement.dao.Admindao;
import com.hospital.hospitalmanagement.dao.Doctordao;
import com.hospital.hospitalmanagement.dao.Patientdao;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.service.Adminservice;
import com.hospital.hospitalmanagement.service.Doctorservice;
import com.hospital.hospitalmanagement.service.Patientservice;
import com.hospital.hospitalmanagement.validation.Loginvalidation;
import com.hospital.hospitalmanagement.validation.Validation;

@Controller
public class PatientController {
	Patientdao patientdao=new Patientdao();
	
	Patientservice patientservice=new Patientservice();
	
	Adminservice adminservice=new Adminservice();
	
	Doctorservice doctorservice=new Doctorservice();
	
	Admindao admindao=new Admindao();
	Doctordao doctordao=new Doctordao();
	
	Logger logger=LoggerFactory.getLogger(PatientController.class);
	@GetMapping("/patientlogin")
	public String patientLogin(@ModelAttribute("login")Admin admin,Model model,HttpSession session) throws SQLException {
		return "patientlogin.html";
		
	}
	@GetMapping("/logpatient")
	public String patientsLogin(@ModelAttribute("login")Admin admin,Model model,HttpSession session) throws SQLException, JsonProcessingException, Loginvalidation {

		logger.info("patient login page");
		logger.info("Patient login page connect");
		if(Boolean.TRUE.equals(patientdao.plogin(admin, session))) {
		Integer appCount = admindao.appointmentCounts(admin);
        model.addAttribute("appointmentCounts", appCount);
		patientservice.doctordetail(model);
		patientdao.doctorList(model);
		return "dashboard.html";
		}
		else
		return "validation.html";
	}
	@PostMapping("/addpatient")
	public String patientProfile(@ModelAttribute("patientprofile")Patient patient,Model model,HttpSession session){
	    Validation valid=new Validation();
		logger.info("Patient profile add");
		for (int i = 1; i <=18; i++) {
            session.removeAttribute("errorMessage1" + i);
        }
		if(Boolean.FALSE.equals(valid.gendervalidation(patient.getPatientGender(), model))||Boolean.FALSE.equals(valid.agevalidation(patient.getPatientAge(), model))||Boolean.FALSE.equals(valid.addressvalidation(patient.getPatientAddress(), model))){
			for (int j = 1; j <=18; j++) {
	            if (model.getAttribute("errorMessage" + j) != null) {
	                String errorMessage = (String) model.getAttribute("errorMessage" + j);
	                model.addAttribute("ErrorMessage", errorMessage);
	              
	            }
			}
	      return "validation.html";	
			
		}
		if(Boolean.TRUE.equals(patientdao.addProfile(patient))){
			
		 return "dashboard.html";
		}
		else
		return "validationpopup.html";
	}
	@PostMapping("/precription")
	public String precription(@ModelAttribute("checkbox")Doctor doctor,Admin admin,Model model) throws Exception {
		
	    logger.info("precription added");
        adminservice.precriptionadd(doctor,admin);
        Integer patientcount = doctordao.patientCount();
        model.addAttribute("patientCount", patientcount);
        Integer approvalcount = doctordao.approvedCount();
        model.addAttribute("approvedCount", approvalcount);
		return "doctordashboard.html";
	}

	@GetMapping("/dlist")
	public String doctorList(Model model) {
		logger.info("Doctor list");
		patientservice.doctordetail(model);
		return "/jsp/listdoctor.jsp";
		
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/jsp/Home1.jsp";
		
	}
	@PostMapping("/applyappointment")
	public String appointmentApproved(@RequestParam("check")String ano,Model model) {

	    String[] arno=ano.split("[, ]", 0);
		ArrayList<String> applyapp=new ArrayList<>();
		for(String nameList:arno) {
			Collections.addAll(applyapp, nameList);
			applyapp.add(nameList);
			
		}
		
		String aprno=applyapp.get(0);
		String pid=applyapp.get(1);


		model.addAttribute("docid",aprno);
		model.addAttribute("docname", pid);
		logger.info("Patient appointment");
		
		return "/jsp/getappointment.jsp";
		
	}


	@GetMapping("/profile")
	public String phistoryList(@RequestParam("sid")Integer pid,Model model) throws Exception {
		Patient patient=new Patient();
		patient.setPatientId(pid);
		List<Patient> patienthistory=admindao.profileDetail(patient, model);
		model.addAttribute("profile", patienthistory);
		logger.info("Patient history");
		return "/jsp/patientprofile.jsp";
		
	}
	@GetMapping("/mymedi")
	public String medicineList(@RequestParam("sid")Integer pid,Model model) throws Exception {
		Doctor doctor=new Doctor();
		doctor.setPatientId(pid);
		List<Doctor> mymedi=admindao.mymedicineDetail(doctor, model);
		model.addAttribute("profile", mymedi);
		logger.info("My medicine");
		return "/jsp/patientprofile.jsp";
		
	}
	@PostMapping("/adminupdate")
	public String adminUpdate(@RequestParam("pid")Integer id,@RequestParam("name")String name) {
		Admin admin=new Admin();
		admin.setAdminId(id);
		admin.setAdminName(name);
		logger.info("Admin profile update");
		admindao.adminUpdateprofile(admin);
		return "/jsp/admindashboard.jsp";
		
	}
	@PostMapping("/gappointment")
	public String appointmentApproval(@RequestParam("id")Integer arno,@RequestParam("pid1")Integer did,
			@RequestParam("pid")Integer pid,@RequestParam("date1")Date adate,@RequestParam("disea")String status) throws Exception {
		Doctor doctor=new Doctor();

		doctor.setRequestNo(arno);
		doctor.setDoctorId(did);
		doctor.setPatientId(pid);
		doctor.setAppointmentDate(adate);
		doctor.setStatus(status);
	    logger.info("get Appointment");
        doctordao.giveappointment(doctor);
		return "/jsp/Doctordashboard.jsp";
	}
	@PostMapping("/dspecial")
	public String doctorSpecial(@RequestParam("special")String sp,Model m) {
		Doctor doctor=new Doctor();
		doctor.setSpecialization(sp);
		patientservice.doctorSpecial(doctor, m);
		return "/jsp/listdoctor.jsp";
		
	}

}
