package com.hospital.hospitalmanagement.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hospital.hospitalmanagement.dao.Admindao;
import com.hospital.hospitalmanagement.dao.Doctordao;
import com.hospital.hospitalmanagement.dao.Patientdao;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.service.Adminservice;
import com.hospital.hospitalmanagement.service.Patientservice;
import com.hospital.hospitalmanagement.validation.Validation;

@Controller
public class AdminController {

	Patientdao patientdao =new Patientdao();

	Patientservice patientservice=new Patientservice();
	
	Admindao admindao=new Admindao();

	Doctordao doctordao=new Doctordao();
	
	Adminservice adminservice=new Adminservice();
	Logger logger=LoggerFactory.getLogger(AdminController.class);
	

	@GetMapping("/adminlogin")
	public String adminLoginPage(@ModelAttribute("adminlog")Admin admin) {
		return "adminlogin.html";
	}
	@PostMapping("/logadmin")
	public String adminLogin(@ModelAttribute("adminlog")Admin admin,Model model,HttpSession session) throws Exception {
	
		logger.info("Admin login page connect");
		if(Boolean.TRUE.equals(admindao.adminLogin(admin, session))){
		Integer doctorcount = admindao.doctorCount();
        model.addAttribute("doctorCount", doctorcount);
        Integer patientcount = admindao.patientCount();
        model.addAttribute("patientCount", patientcount);
        Integer appointmentcount = admindao.appointmentCount();
        model.addAttribute("appointmentCount", appointmentcount);
		return "admindashboard.html";
		}
		else 
			return "validation.html";
		
	}

	@PostMapping("/adminadd")
	public String adminProfile(@RequestParam("id")Integer id,@RequestParam("name")String aname,
			@RequestParam("gender")String gender,@RequestParam("age") Integer age,@RequestParam("address") String address,
			@RequestParam("mail")String mail,@RequestParam("mobileno")Long mno) {
		Admin admin=new Admin();
	
		admin.setRegistId(id);
		admin.setAdminName(aname);
		admin.setGender(gender);
		admin.setAge(age);
		admin.setAddress(address);
		admin.setMail(mail);
		admin.setMobileNo(mno);
		
		logger.info("Admin profile add");
        adminservice.profileofdoctor(admin);
		return "/jsp/admindashboard.jsp";
		
	}
	
	

	@PostMapping("/billopen")
	public String addBill(@ModelAttribute("checkbox")Admin admin,Model model) throws Exception {
		String pno=admin.getMail();
	
	    String[] prno=pno.split("[, ]", 0);
		ArrayList<String> billopen=new ArrayList<>();
		for(String nameList:prno) {
			billopen.add(nameList);
		}
		String bno=billopen.get(0);
		String pid=billopen.get(1);
		String dfee=billopen.get(2);
		String mfee=billopen.get(3);
		String amt=billopen.get(4);
		String status=billopen.get(5);
		String bills=billopen.get(6);
		model.addAttribute("billno",bno);
        model.addAttribute("paid",pid);
        Integer patid=Integer.parseInt(pid);
        admin.setPatientsId(patid);
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		long milliSeconds= Long.parseLong(bills);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		String appDate1=formatter.format(calendar.getTime());
        model.addAttribute("df", dfee);
        model.addAttribute("mf", mfee);
        model.addAttribute("tamount", amt);
        model.addAttribute("pay", status);
        model.addAttribute("dateBill", appDate1);
        
        admindao.billName(admin,model);
		return "hospitalBill.html";
		
	}

	@GetMapping("/visitdate")
	public String patientSearch(@RequestParam("appdate")String vdate,Model model) {
		Doctor doctor=new Doctor();
		doctor.setAppointmentTime(vdate);
		
		adminservice.listofpdate(doctor, model);
		return "patienthistory.html";
		
	}

	@PostMapping("/paymentprocess")
	public String paymentProceed(@ModelAttribute("checkbox")Admin admin,HttpSession session) {


			patientservice.paymentPay(admin, session);
			logger.info("payprocess update");
			
			return "admindashboard.html";
	}

}
