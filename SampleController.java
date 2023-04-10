package com.hospital.hospitalmanagement.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.hospital.hospitalmanagement.validation.AddressValidation;
import com.hospital.hospitalmanagement.validation.Idvalidation1;
import com.hospital.hospitalmanagement.validation.Invalidname;
import com.hospital.hospitalmanagement.validation.Loginvalidation;
import com.hospital.hospitalmanagement.validation.MailidValidation1;
import com.hospital.hospitalmanagement.validation.Mailidvalidation;
import com.hospital.hospitalmanagement.validation.Mnovalidation;
import com.hospital.hospitalmanagement.validation.PasswordValidation;
import com.hospital.hospitalmanagement.validation.Validation;

@Controller
public class SampleController {
	
	Patientdao patientdao =new Patientdao();

	Patientservice patientservice=new Patientservice();
	
	Admindao admindao=new Admindao();

	Doctordao doctordao=new Doctordao();
	Doctorservice doctorservice=new Doctorservice();
	
	Adminservice adminservice=new Adminservice();
Logger logger=LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("/")
	public String landing() {
		logger.info("Connected Welcome page");
		return "Home1.html";	
	}
	@RequestMapping("/about")
	public String about() {
		return "about.html";		
	}
	@RequestMapping("/contact")
	public String contact() {
		return "contact.html";		
	}
	@RequestMapping("/blog")
	public String blog() {
		return "Blog.html";		
	}
	@GetMapping("/regis")
	public String registration(@ModelAttribute("regi")Admin admin){
        logger.info("reg page");
		
        
		return "register.html";
		
	}
	@GetMapping("/register")
	public String register(@ModelAttribute("regi")Admin admin, HttpSession session,Model model) throws Invalidname{
		Validation v=new Validation();
		for (int i = 1; i <=5; i++) {
            session.removeAttribute("errorMessage1" + i);
        }
		if(Boolean.FALSE.equals(v.namevalidation(admin.getName(), model))||Boolean.FALSE.equals(v.pwdvalidation(admin.getPassword(), model))||Boolean.FALSE.equals(admindao.mailValidation(admin, model))||Boolean.FALSE.equals(v.mnovalidation(admin.getMobileNo(), model))){
			for (int j = 1; j <=5; j++) {
	            if (model.getAttribute("errorMessage" + j) != null) {
	                String errorMessage = (String) model.getAttribute("errorMessage" + j);
	                model.addAttribute("ErrorMessage", errorMessage);
	            }
			}
				return "validation.html";
		}	
		  admindao.registration(admin, session);
		return "Popup.hmtl";
	}	
	
	@GetMapping("/patient")
	public String patientLogin(@ModelAttribute("patientprofile")Patient patient){
		return "patientaddprofile.html";
		
	}
	@PostMapping("/view")
	public String getMedicine(@ModelAttribute("sid")Patient patient,Model model) {
		doctordao.getMedicine(patient, model);
		return "medipopup.html";
		
	}
	@PostMapping("/searchApp")
	public String appointmentSearch(@ModelAttribute("ano")Patient patient,Model model) {		
		patientdao.appointmentStatus(patient, model);
		return "statuspopup.html";
		
	}
	
	@GetMapping("/profilepatient")
	public String phistoryList(@ModelAttribute("patients")Patient patient,Model model) throws Exception {
		
		List<Patient> patienthistory=admindao.profileDetail(patient, model);
		model.addAttribute("profile", patienthistory);
		logger.info("Patient history");
		return "patientprofile.html";
		
	}
	@PostMapping("/pupdate")
	public String patientUpdate(@ModelAttribute("patientedit")Patient patient,Model model,HttpSession session) throws Exception {
	    Validation valid=new Validation();
		logger.info("Patient profile update");
		for (int i = 1; i <=18; i++) {
            session.removeAttribute("errorMessage1" + i);
        }
		if(Boolean.FALSE.equals(valid.idvalidation1(patient.getRegistId(), model))||Boolean.FALSE.equals(valid.idvalidation(patient.getRegistId(), model))||Boolean.FALSE.equals(valid.namevalidation(patient.getPatientName(), model))||Boolean.FALSE.equals(valid.gendervalidation(patient.getPatientGender(), model))||Boolean.FALSE.equals(valid.agevalidation(patient.getPatientAge(), model))||Boolean.FALSE.equals(valid.addressvalidation(patient.getPatientAddress(), model))||Boolean.FALSE.equals(valid.mailvalidation(patient.getPatientMail(), model))||Boolean.FALSE.equals(valid.mnovalidation(patient.getMobileNumber(), model))){
			for (int j = 1; j <=18; j++) {
	            if (model.getAttribute("errorMessage" + j) != null) {
	                String errorMessage = (String) model.getAttribute("errorMessage" + j);
	                model.addAttribute("ErrorMessage", errorMessage);
	            }
			}
				return "validation.html";
		}
		patientservice.patientUpdateprofile(patient);
		return "dashboard.hmtl";

	}
	@GetMapping("/openpatientedit")
	public String patientUpdate(@ModelAttribute("patientedit")Admin admin) {
		return "patientedit.html";
	}
	@GetMapping("/patienthistory")
	public String phistoryList(@ModelAttribute("phis")Doctor doctor,Model model) throws JsonProcessingException {
		
		adminservice.listofphistory(doctor, model);
		return "patientsHistory.html";
		
	}
	@GetMapping("/myappointment")
	public String appStatus(@ModelAttribute("app")Patient patient,Model model) throws JsonProcessingException {
		admindao.appointmentDetail(patient, model);
		return "liststatus.html";
		
	}
	@GetMapping("/billlist")
	public String billList(@ModelAttribute("bill")Admin admin,Model model) throws JsonProcessingException {
  
		adminservice.viewBilllist(admin,model);
		logger.info("Bill list");
		return "listbill.html";
		
	}
	@PostMapping("/viewbill")
	public String addPayment(@ModelAttribute("checkbox")Admin admin,Model model) {
		String pno=admin.getMail();
		
	    String[] prno=pno.split("[, ]", 0);
		ArrayList<String> viewbill=new ArrayList<>();
		for(String nameList:prno) {
			viewbill.add(nameList);
		}
		String pid=viewbill.get(0);
		String amt=viewbill.get(1);
		model.addAttribute("billno",pid);
        model.addAttribute("amount",amt);
        logger.info("Patient viewbill");
		return "paymentform.html";
		
	}
	@GetMapping("/mybill")
	public String mybillCredit(@ModelAttribute("pbill")Admin admin,Model model) throws JsonProcessingException {

		patientservice.mybillcrdit(admin, model);
		logger.info("My bill creation");
		return "ListPaydetails.html";
		
	}
	@GetMapping("/doctorlist")
	public String doctorList(Model model) throws JsonProcessingException {
		logger.info("Doctor list");
		patientdao.doctorList(model);
		return "listdoctors.html";
		
	}
	@GetMapping("/appointments")
	public String appointmentApproved(@ModelAttribute("checkdid")Patient patient,Model model) {
		
		String no=patient.getPatientGender();

	    String[] dno=no.split("[, ]", 0);
		ArrayList<String> applyApp=new ArrayList<>();
		for(String nameList:dno) {
    		applyApp.add(nameList);
			
    	}
	    String aprno=applyApp.get(0);
	    String special=applyApp.get(1);
		String pid=applyApp.get(2);


		model.addAttribute("doctorid",aprno);
		model.addAttribute("specialization", special);
		model.addAttribute("docname", pid);
		logger.info("Patient appointment");

		
		return "getappointment1.html";
		
	}
	@PostMapping("/appointmentadd")
	public String appointmentApplication(@ModelAttribute("appointment")Patient patient,Model model) throws Exception {

	    logger.info("Appointment applied");	 
	    if(Boolean.TRUE.equals(patientdao.getAppointments(patient))) {
	    	
	    	return "dashboard.html";
	    }
	    String error="Already booked Appointment for this date";
	    model.addAttribute("invalid", error);
		return "validationpopup.html";
		
	}
	
	@GetMapping("/doctor")
	public String doctorProfile(@ModelAttribute("doctorprofile")Doctor doctor){
		return "doctoraddprofile.html";
		
	}
	@GetMapping("/applist")
	public String viewDoctorList(@ModelAttribute("alist")Patient patient,Model model) throws JsonProcessingException {

		doctorservice.listofdoctor(patient,model);
		return "listappointmentgrid.html";
		
	}
	@GetMapping("/approvalist")
	public String approvalList(@ModelAttribute("approved")Doctor doctor,Model model) throws JsonProcessingException {
	
		doctorservice.listofapproval(doctor, model);
		return "listapprovals.html";
		
	}
	@PostMapping("/giveappoint")
	public String appointmentStatus(@ModelAttribute("check")Doctor doctor,Model model) {
		String no=doctor.getDoctorGender();
		logger.info(no);

	    String[] arno=no.split("[, ]", 0);
		ArrayList<String> giveapp=new ArrayList<>();
		for(String nameList:arno) {
			giveapp.add(nameList);
		}
		
		String aprno=giveapp.get(0);
		String appDate=giveapp.get(1);
		String symtomps=giveapp.get(2);
		String did=giveapp.get(3);
		String pid=giveapp.get(4);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		long milliSeconds= Long.parseLong(appDate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		String appDate1=formatter.format(calendar.getTime());

		
		model.addAttribute("apprequest",aprno);
		model.addAttribute("patientdate", appDate1);
		model.addAttribute("reason", symtomps);
		model.addAttribute("doctorsId",did);
		model.addAttribute("patid", pid);	
		return "giveappointment1.html";
		
	}

@PostMapping("/giveappoints")
	public String appointmentDateChange(@ModelAttribute("check")Doctor doctor,Model model) {
		String no=doctor.getDoctorGender();
		logger.info(no);
	    String[] arno=no.split("[, ]", 0);
		ArrayList<String> giveapp=new ArrayList<>();
		for(String nameList:arno) {
			giveapp.add(nameList);
		}		
		String aprno=giveapp.get(0);
		String appDate=giveapp.get(1);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		long milliSeconds= Long.parseLong(appDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		String appDate1=formatter.format(calendar.getTime());		
		model.addAttribute("apprequestNo",aprno);
		model.addAttribute("changedate", appDate1);
		return "giveappointment.html";
		
	}
	@PostMapping("/givemedicine")
	public String addMedicine(@ModelAttribute("checkbox")Doctor doctor,Model model) {
		String ano=doctor.getDoctorGender();
	    String[] arno=ano.split("[, ]", 0);
		ArrayList<String> medilist=new ArrayList<>();
		for(String nameList:arno) {
			medilist.add(nameList);
		}
		
		String apno=medilist.get(0);
		String did=medilist.get(1);
	    String pid=medilist.get(2);
		
		model.addAttribute("appno",apno);
		model.addAttribute("docid",did);
		model.addAttribute("patid",pid);
		
		return "givemedicine.html";
		
	}

	@PostMapping("/appointmentstat")
	public String appointmentApproval(@ModelAttribute("giveapp")Doctor doctor,Model model) throws Exception {
	    logger.info("get Appointment");
	    String startDate=doctor.getAppointmentTime(); // Input String
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy"); // New Pattern
        java.util.Date date = sdf1.parse(startDate); // Returns a Date format object with the pattern
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());// Outputs : 2013-02-01
        doctor.setAppointmentDate(sqlStartDate);
        System.out.println(sqlStartDate+"sqlstartdate");
	    if(Boolean.TRUE.equals(doctordao.giveappointment(doctor))) {
        return "doctordashboard.html";
	    }
	    String error="Invalid appointmentNo";
	    model.addAttribute("invalid", error);
	    return "validationpopup.html";
	}
	@PostMapping("/appointmentChange")
	public String changeAppointment(@ModelAttribute("giveapp")Doctor doctor,Model model) {
		patientdao.appointmentChange(doctor);
		return "doctordashboard.html";
		
	}
	@PostMapping("/accept")
	public String acceptAppointment(@ModelAttribute("checkdate")Patient patient,Model model) {
		String checkno=patient.getPatientGender();
		System.out.println(checkno+"checkno");
		 Integer patid=Integer.parseInt(checkno);
		 patient.setRegistId(patid);
		patientdao.appointmentAccept(patient);
		return "dashboard.html";
		
	}
	@GetMapping("/doctorprofile")
	public String doctorProfile(@ModelAttribute("dpro")Doctor doctor,Model model)throws Exception  {
		
		doctorservice.historyofpatient(model, doctor);
		logger.info("doctor profile page");
		return "doctorprofile.html";
		
	}
	@GetMapping("/opendocupdate")
	public String doctorEdit(@ModelAttribute("patientedit")Admin admin) {
		return "doctorupdateprofile.html";
	}
	@PostMapping("/Dupdate")
	public String doctorUpdate(@ModelAttribute("docup")Doctor doctor,Model model,HttpSession session){
        Validation valid=new Validation();
		logger.info("doctor profile update");
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
		adminservice.updateprofile(doctor);
		return "doctordashboard.hmtl";
	}
	@GetMapping("/doctorhistory")
	public String dhistoryList(@ModelAttribute("dhis")Doctor doctor,Model model)throws Exception {

		doctorservice.historyofdoctor(model, doctor);
		logger.info("Doctor history page");
		return "doctorsHistory.html";
		
	}
	@GetMapping("/admin")
	public String adminProfile(@ModelAttribute("adminprofile")Admin admin){
		return "adminaddprofile.html";
		
	}
	@PostMapping("/adminaddpro")
	public String adminProfileAdd(@ModelAttribute("adminprofile")Admin admin,HttpSession session,Model model) throws Exception {
	    Validation valid=new Validation();
		logger.info("Admin profile add");
		if(Boolean.TRUE.equals(valid.idvalidation1(admin.getRegistrationId(), model)&&valid.idvalidation(admin.getRegistrationId(), model)&&valid.namevalidation(admin.getAdminName(), model)
		           &&valid.gendervalidation(admin.getGender(), model)&&valid.agevalidation(admin.getAge(), model)&&valid.addressvalidation(admin.getAddress(), model)&&valid.mailvalidation(admin.getMail(), model)&&valid.mnovalidation(admin.getMobileNo(), model))) {
        adminservice.profileofdoctor(admin);
		return "admindashboard.html";
		}
		else
			return "validation.html";
	}
	@GetMapping("/precriptionlist")
	public String precriptionList(Model model) throws JsonProcessingException {
		adminservice.listprecription(model);
		return "ListPrescription.html";
		
	}
	@GetMapping("/paymentlist")
	public String payableList(Model model) throws JsonProcessingException {
		adminservice.payable(model);
		return "ListPayment.html";
		
	}
	@GetMapping("/billcre")
	public String billCredit(Admin admin,Model model) throws JsonProcessingException {
		
		doctorservice.billcrdit(admin,model);
		logger.info("bill create");
		return "ListPaydetails.html";
		
	}
	@PostMapping("/billcreat")
	public String addMedicine(@ModelAttribute("checkbox")Admin admin,Model model) {
		String pno=admin.getCatogories();
	    String[] prno=pno.split("[, ]", 0);
		ArrayList<String> billcreat=new ArrayList<>();
		for(String nameList:prno) {
			billcreat.add(nameList);
		}
		String pid=billcreat.get(0);
		String preno=billcreat.get(1);	
		model.addAttribute("appointNo",preno);
		model.addAttribute("patient",pid);		
		return "bill.html";
		
	}
	@PostMapping("/bill")
	public String billFill(@ModelAttribute("checkbox")Admin admin,Model model) throws Exception {
	
	    logger.info("Bill Created");
        doctorservice.billfilled(admin);
		return "admindashboard.html";
		
	}
	@GetMapping("/logedout")
	public String logedout(HttpSession session) {
		session.invalidate();
		return "Home1.html";	
	}
}
