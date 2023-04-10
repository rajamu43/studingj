package com.hospital.hospitalmanagement.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.hospital.hospitalmanagement.dao.Admindao;
import com.hospital.hospitalmanagement.dao.Doctordao;
import com.hospital.hospitalmanagement.dao.Patientdao;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.service.Doctorservice;
import com.hospital.hospitalmanagement.service.Patientservice;
import com.hospital.hospitalmanagement.validation.AddressValidation;
import com.hospital.hospitalmanagement.validation.Mailidvalidation;
import com.hospital.hospitalmanagement.validation.Validation;

@Controller
public class DoctorController {
	Doctordao doctordao=new Doctordao();
	Admindao admindao=new Admindao();
	
	Patientdao patientdao=new Patientdao();
	
	Patientservice patientservice=new Patientservice();
	
	Doctorservice doctorservice=new Doctorservice();
	Logger logger=LoggerFactory.getLogger(DoctorController.class);
	@GetMapping("/doctorlogin")
	public String doctorLogin(@ModelAttribute("doctorlog")Admin admin){
		return "doctorlogin.html";
		
	}
	@PostMapping("/logdoctor")
	public String doctorLoginPage(@ModelAttribute("doctorlog")Admin admin,Model m,HttpSession session) throws Exception {

		if(Boolean.TRUE.equals(doctordao.dlogin(admin, session))) {
			Integer patientcount = doctordao.patientCount();
	        m.addAttribute("patientCount", patientcount);
	        Integer approvalcount = doctordao.approvedCount();
	        m.addAttribute("approvedCount", approvalcount);
	        return "doctordashboard.html";
		}
		else
			return "validation.html";
	}
	
	@PostMapping("/addprofile")
	public String doctorProfileadd(@ModelAttribute("doctorprofile")Doctor doctor,HttpSession session,Model model)   {
	    Validation valid=new Validation();
		logger.info("Doctor profile add");
		for (int i = 1; i <=18; i++) {
            session.removeAttribute("errorMessage1" + i);
        }
		if(Boolean.FALSE.equals(valid.idvalidation1(doctor.getRegisterId(), model))||Boolean.FALSE.equals(valid.idvalidation(doctor.getRegisterId(), model))||Boolean.FALSE.equals(valid.namevalidation(doctor.getDoctorName(), model))||Boolean.FALSE.equals(valid.gendervalidation(doctor.getDoctorGender(), model))||Boolean.FALSE.equals(valid.agevalidation(doctor.getDoctorAge(), model))||Boolean.FALSE.equals(valid.addressvalidation(doctor.getDoctorAddress(), model))||Boolean.FALSE.equals(valid.mailvalidation(doctor.getDoctorMail(), model))||Boolean.FALSE.equals(valid.mnovalidation(doctor.getMobileNo(), model))){
			for (int j = 1; j <=18; j++) {
                if (model.getAttribute("errorMessage" + j) != null) {
                    String errorMessage = (String) model.getAttribute("errorMessage" + j);
                    model.addAttribute("ErrorMessage", errorMessage);
                }
            }
            return "validation.html";
        }
	    doctorservice.doctoraddprofile(doctor);
		return "doctordashboard.html";	
	}

	@PostMapping("/giveappointment")
	public String appointmentStatus(@RequestParam("check")String ano,Model model) {
	
	    String[] arno=ano.split("[, ]", 0);
		ArrayList<String> giveapp=new ArrayList<>();
		for(String nameList:arno) {
			giveapp.add(nameList);
		}
		
		String aprno=giveapp.get(0);
		String adate=giveapp.get(1);
		String did=giveapp.get(2);
		String pid=giveapp.get(3);
		
		model.addAttribute("apprequest",aprno);
		model.addAttribute("appdate", adate);
		model.addAttribute("docid",did);
		model.addAttribute("patid", pid);
		
		return "/jsp/giveappointment.jsp";
		
	}

	@PostMapping("/paycheck")
	public String payamount(@ModelAttribute("checkbox")Admin admin,Model model) throws Exception {
		String pno=admin.getMail();
	    String[] prno=pno.split("[, ]", 0);
		ArrayList<String> paymentcheck=new ArrayList<>();
		for(String nameList:prno) {
			paymentcheck.add(nameList);
		}
		String bno=paymentcheck.get(0);
		String trno=paymentcheck.get(1);
	    String paymentDate=paymentcheck.get(2);
		Integer billno=Integer.parseInt(bno);
		admin.setBillNo(billno);
		Integer tno=Integer.parseInt(trno);
		admin.setAppointmentNo(tno);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		long milliSeconds= Long.parseLong(paymentDate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		String appDate1=formatter.format(calendar.getTime());
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
	        java.util.Date date = sdf1.parse(appDate1); // Returns a Date format object with the pattern
	        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());// Outputs : 2013-02-01
	        admin.setAppointmentDate(sqlStartDate);
		admindao.paycheck(admin);
		model.addAttribute("billno",bno);
		model.addAttribute("trano",trno);
		model.addAttribute("getDate", appDate1);
		logger.info(bno);
		logger.info(trno);
		logger.info("paycheck page");
		return "admindashboard.html";
		
	}

	
}
