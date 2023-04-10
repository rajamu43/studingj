package com.hospital.hospitalmanagement.dao;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.hospitalmanagement.controller.ConnectionUtil;
import com.hospital.hospitalmanagement.mapper.Appointmentgetmapper;
import com.hospital.hospitalmanagement.mapper.BillListmapper;
import com.hospital.hospitalmanagement.mapper.Doctorlistmapper;
import com.hospital.hospitalmanagement.mapper.GetappointmentMapper;
import com.hospital.hospitalmanagement.mapper.GetdateMapper;
import com.hospital.hospitalmanagement.mapper.Medistatusmapper;
import com.hospital.hospitalmanagement.mapper.Patienthistorymapper;
import com.hospital.hospitalmanagement.mapper.PrecriptionMapper;
import com.hospital.hospitalmanagement.mapper.ProfileMapper;
import com.hospital.hospitalmanagement.mapper.RegisterMapper;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.validation.Appointmentreqvalidation;
import com.hospital.hospitalmanagement.validation.Loginvalidation;


public class Patientdao implements PatientInterface {
	JdbcTemplate jdbctemplate = ConnectionUtil.getJdbcTemplate();
	Logger print=LoggerFactory.getLogger(Patientdao.class);
	Random rand = new Random();
	int maximum = 5000;

	int randomNum = rand.nextInt((maximum)+1);
	
	public Boolean plogin(Admin admin,HttpSession session) {
		print.info("Patient");
	
		Integer regNo=admin.getRegistrationId();
	
		String patientpwd=admin.getPassword();
		Integer regNo1 = null;
		String log="select registrationid,registrationname,password,role,mailid,mobileno from registration2 where registrationid=?";
		List<Admin> login=(List<Admin>) jdbctemplate.query(log, new RegisterMapper(),regNo);
	  
		for (Admin adminpojo : login) {
			 regNo1=admin.getRegistrationId();
			if(regNo1.equals(regNo)) {
				String patientpwd1=adminpojo.getPassword();
				if(patientpwd1.equals(patientpwd)) {
					 String roles=adminpojo.getCatogories();
					 if(roles.equals("patient")) {
						String pName=adminpojo.getName();
						 String mail=adminpojo.getMail();
						 Long mno=adminpojo.getMobileNo();
						 session.setAttribute("regno", regNo1);
						 session.setAttribute("roles", roles);
						 session.setAttribute("regname", pName);
						 session.setAttribute("mail", mail);
						 session.setAttribute("mobile", mno);
						 print.info("patient login");
						 
					 }	
				}
				else {
					return false;
				}
					
			}
			
		}
        
		print.info("Streams");
		Stream<Admin> stream=login.stream();
		stream.forEach(System.out::println);
		login.stream()
		       .filter(Admin -> admin.getRegistrationId() == 1206)
		       .forEach(Admin -> print.info("The patient id is valid"));
        print.info("patientdao page");
		return true;
	}

	public Boolean addProfile(Patient patient) {
		print.info("Patient Profile page");
		Integer patientId=patient.getRegistId();
		String checkPatientid="select regid from patientprofiles where regid=?";
		Integer patientsId=jdbctemplate.queryForObject(checkPatientid, Integer.class,patientId);
	
		if(patientId.equals(patientsId)) {
			
			return false;
		}
		else {
		String adminprofile="insert into patientprofiles values(?,?,?,?,?,?,?,?)";
		Object[] add= {patient.getRegistId(),patient.getPatientName(),patient.getPatientGender(),patient.getPatientAge(),patient.getPatientAddress(),patient.getPatientMail(),patient.getMobileNumber(),patient.getDiseaes()};
		jdbctemplate.update(adminprofile,add);
		}
		return true;
		
	}
	public void updateProfile(Patient patient) {
		print.info("Patient update page");
		String adminprofiles="update patientprofiles set patientname=?,gender=?,age=?,address=?,mailid=?,mobileNo=?,disease=? where regid=?";
		Object[] update= {patient.getPatientName(),patient.getPatientGender(),patient.getPatientAge(),patient.getPatientAddress(),patient.getPatientMail(),patient.getMobileNumber(),patient.getDiseaes(),patient.getRegistId()};
		jdbctemplate.update(adminprofiles,update);
	}
	public void removeProfile(Patient patient) {
		print.info("Patient remove page");
		String adminremove="delete from patientprofiles where regid=?";
		Object[] remove= {patient.getRegistId()};
		jdbctemplate.update(adminremove,remove);
	}
	public List<Doctor> listofDoctors(){
		String list="select regid,doctorname,gender,age,address,mailid,mobileNo,specialization from doctorprofiles";
		
		print.info("list of doctors");
		return jdbctemplate.query(list, new Doctorlistmapper());
	}
	public void doctorList(Model model) throws JsonProcessingException{
		String list="select regid,doctorname,gender,age,address,mailid,mobileNo,specialization from doctorprofiles";
		List<Doctor>listview=jdbctemplate.query(list, new Doctorlistmapper());
		List<Map<String,Object>>doclist=new ArrayList<>();
		for (Doctor doctor : listview) {
			Map<String,Object>docView=new HashMap<>();
			docView.put("registid", doctor.getRegisterId());
			docView.put("doctors", doctor.getDoctorName());
			docView.put("doctorgender", doctor.getDoctorGender());
			docView.put("doctorage", doctor.getDoctorAge());
			docView.put("doctoraddress",doctor.getDoctorAddress());
			docView.put("doctormail", doctor.getDoctorMail());
			docView.put("doctormobile", doctor.getMobileNo());
			docView.put("docspecial", doctor.getSpecialization());
			doclist.add(docView);
		}
		List<Map<String,Object>>doclists=doclist.stream().toList();
		ObjectMapper mapper=new ObjectMapper();
		String listDoc;		
			listDoc = mapper.writeValueAsString(doclist);
			model.addAttribute("listdoctor",listDoc);
			print.info(listDoc);
			print.info("list of doctors");		
	}
	

	public Boolean getAppointments(Patient patient){
		print.info("appointment page");
		Date patientDate=patient.getDate();
		Integer pid=patient.getPatientId();
		
		String log="select  COUNT(*) from appointmentapplications where appointmentdate=? and patientid=?";
		Integer count=jdbctemplate.queryForObject(log, Integer.class,patientDate,pid);
			if(count==0) {
			    print.info("appointmentdate got it.");			
                print.info("get appointment");
                print.info(patient.getPatientName());
				String appointment="insert into appointmentapplications(appointmentreqno,appointmentdate,patientid,patientname,doctorid,doctorname,disease,appointmentstatus) values(app_reg1.nextval,?,?,?,?,?,?,'pendingap')";
				Object[] add= {patient.getDate(),patient.getPatientId(),patient.getPatientName(),patient.getDoctorId(),patient.getDoctorName(),patient.getDiseaes()};
				jdbctemplate.update(appointment,add);
			}else {
					return false;
			}
			return true;	
}
	public void appointmentAccept(Patient patient){
		print.info("appointment page");
		
		Integer pid=patient.getRegistId();
		System.out.println(pid+"patientid");
//		String log="select  appointmentreqno from appointmentapplications where appointmentreqno=?";
//		String count=jdbctemplate.queryForObject(log, String.class,pid);
//			if(count.equals("datechange")) {
//			    print.info("appointmentdate got it.");			
//                print.info("get appointment");
//                print.info(patient.getPatientName());
                String addmedi="update appointmentapplications set appointmentstatus='pendingap' where appointmentreqno=?";
				Object[] medicine= {patient.getRegistId()};
				jdbctemplate.update(addmedi, medicine);
			//}
}
	public void appointmentChange(Doctor doctor) {
		Integer reqNo=doctor.getRequestNo();
		Date dataChange=doctor.getAppointmentDate();
		String log="select appointmentreqno,doctorname,appointmentstatus,appointmentdate from appointmentapplications where appointmentreqno=?";
		List<Doctor> reg=(List<Doctor>) jdbctemplate.query(log, new Appointmentgetmapper(),reqNo);
		for (Doctor doctorpojo : reg) {
			Integer regno1=doctor.getRequestNo();
			
			if(regno1.equals(reqNo)) {
			print.info("appointmentrequest no got it.");
			String pres=doctorpojo.getPrecription();
   			Date changabledate=doctorpojo.getAppointmentDate();
			if(pres.equals("pendingap")) {
				System.out.println("The appointment changed");
				String addmedi="update appointmentapplications set appointmentstatus='datechange',appointmentdate=? where appointmentreqno=?";
				Object[] medicine= {doctor.getAppointmentDate(),doctor.getRequestNo()};
				jdbctemplate.update(addmedi, medicine);
				  
			}
			}			
		}
	}
	public void paymentAdd(Admin admin,HttpSession session) {
		print.info("Random number  range"); 
        
		admin.setRegistId(randomNum);
		print.info("payment page");
		String appointments="insert into paymentdetails1(billno,paymentmode,cardorupino,amount,transactionno,transactiondate,checkstatus) values(?,?,?,?,?,?,'pending')";
		Object[] add= {admin.getBillNo(),admin.getPaymentMode(),admin.getPayno(),admin.getAmount(),admin.getRegistId(),admin.getAppointmentDate()};
		session.setAttribute("random", randomNum);
		jdbctemplate.update(appointments,add);
		
	}
	
	
    public List<Admin> mylistofBills(Admin admin,Model model) throws JsonProcessingException{
	    print.info("My list of bills");
	    Integer patientid=admin.getPatientsId();
	    String listmy="select billno,patientid,appointmentno,doctorfees,medicinefees,payamount,paymentstatus,paiddate from billamounts where patientid=?";
	    List<Admin>viewbill=jdbctemplate.query(listmy, new BillListmapper(),patientid);
		List<Map<String,Object>>billCredit=new ArrayList<>();
		for (Admin admins : viewbill) {
			Map<String,Object>billsView=new HashMap<>();
			billsView.put("billsno", admins.getBillNo());
			billsView.put("patientsid", admins.getPatientsId());
			billsView.put("appsno", admins.getAppointmentNo());
			billsView.put("dfees", admins.getDoctorFees());
			billsView.put("mfees",admins.getMedicineFees());
			billsView.put("amount", admins.getAmount());
			billsView.put("catogories", admins.getCatogories());
			billsView.put("billdate", admins.getAppointmentDate());
			billCredit.add(billsView);
		}
		ObjectMapper mapper=new ObjectMapper();
		String apprList=mapper.writeValueAsString(billCredit);	
		model.addAttribute("billShow",apprList);
		print.info(apprList);
	return viewbill;
   }	
   public List<Doctor> specification(Doctor doctor){
	  
		String special=doctor.getSpecialization();
		if(special.equals("teeth")) {
			String temp="dentist";
			doctor.setSpecialization(temp);
		
		}
		else if(special.equals("eye")) {
			String temp="Ophthalmalogist";
			doctor.setSpecialization(temp);
		}
		else if(special.equals("heart")) {
			String temp="Cardiologist";
			doctor.setSpecialization(temp);
		}
		else {
			String temp="general physician";
			doctor.setSpecialization(temp);
		}
		String spec=doctor.getSpecialization();
		print.info(spec);
		String docdetail="select regid,doctorname,gender,age,address,mailid,mobileNo,specialization from doctorprofiles where specialization=?";
		
		return jdbctemplate.query(docdetail,new Doctorlistmapper(),spec);
	   
   }
   public List<Patient> appointmentStatus(Patient patient,Model model){
		String mediName = null;
		String doctorName=null;
		Integer hno=patient.getDoctorId();

		String listbill="select appointmentreqno,appointmentdate,patientid,patientname,doctorid,doctorname,disease,appointmentstatus from appointmentapplications where appointmentreqno=?";
		List<Patient> viewpres=jdbctemplate.query(listbill, new GetappointmentMapper(),hno);
		for (Patient patientpojo : viewpres) {
			Integer arno=patientpojo.getRegistId();
			Date bookDate=patientpojo.getDate();
			Integer historyNo=patientpojo.getDoctorId();
			doctorName=patientpojo.getDoctorName();
			String symtomp=patientpojo.getDiseaes();
	        mediName = patientpojo.getPatientMail();
	        model.addAttribute("reqno",arno);
	        model.addAttribute("appdate", bookDate);
	        model.addAttribute("did",historyNo);
			model.addAttribute("mydoctor",doctorName);
			model.addAttribute("sym", symtomp);
	        model.addAttribute("mystatus",mediName);		 
		}
		
		
	        print.info(mediName);
	        print.info(doctorName);
		return viewpres;
	}
}
