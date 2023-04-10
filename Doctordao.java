package com.hospital.hospitalmanagement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.hospitalmanagement.controller.ConnectionUtil;
import com.hospital.hospitalmanagement.mapper.Appointmentgetmapper;
import com.hospital.hospitalmanagement.mapper.BillListmapper;
import com.hospital.hospitalmanagement.mapper.DoctorHistorymapper;
import com.hospital.hospitalmanagement.mapper.Doctorlistmapper;
import com.hospital.hospitalmanagement.mapper.Doctorprofilrmapper;
import com.hospital.hospitalmanagement.mapper.GetMedicine;
import com.hospital.hospitalmanagement.mapper.GetappointmentMapper;
import com.hospital.hospitalmanagement.mapper.Getapprovalmapper;
import com.hospital.hospitalmanagement.mapper.Historymapper;
import com.hospital.hospitalmanagement.mapper.Medistatusmapper;
import com.hospital.hospitalmanagement.mapper.RegisterMapper;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.validation.Appointmentreqvalidation;
import com.hospital.hospitalmanagement.validation.Loginvalidation;
import com.hospital.hospitalmanagement.validation.Medicinevalidation;


public class Doctordao implements DoctorInterface {
	
	JdbcTemplate jdbctemplate=ConnectionUtil.getJdbcTemplate();
	Logger logger=LoggerFactory.getLogger(Doctordao.class);
	public Boolean dlogin(Admin admin,HttpSession session){
		logger.info("Doctor login page");
		
		Integer regno=admin.getRegistrationId();
		String ppassword=admin.getPassword();
		String doctorlog="select registrationid,password,role,registrationname,mailid,mobileno from registration2 where registrationid=?";
		List<Admin> docreg=(List<Admin>) jdbctemplate.query(doctorlog, new RegisterMapper(),regno);
	    
		for (Admin adminpojo : docreg) {
			Integer regno1=admin.getRegistrationId();
			if(regno1.equals(regno)) {
				String ppassword1=adminpojo.getPassword();
				if(ppassword1.equals(ppassword)) {
					String role=adminpojo.getCatogories();
					if(role.equals("doctor")) {
						String pname=adminpojo.getName();
						String mail=adminpojo.getMail();
						Long mno=adminpojo.getMobileNo();
						session.setAttribute("regno", regno1);
						 session.setAttribute("roles", role);
						 session.setAttribute("docname", pname);
						 session.setAttribute("dmail", mail);
						 session.setAttribute("mob", mno);
					logger.info("login sucess");
					}
					
				}
				else {
				return false;
				}
			}
		}
        logger.info("doctordao page");
		return true;
	}
	
	public void daddProfile(Doctor doctor) {
		logger.info("doctor Profile page");
		String dprofile="insert into doctorprofiles values(?,?,?,?,?,?,?,?)";
		Object[] add= {doctor.getRegisterId(),doctor.getDoctorName(),doctor.getDoctorGender(),doctor.getDoctorAge(),doctor.getDoctorAddress(),doctor.getDoctorMail(),doctor.getMobileNo(),doctor.getSpecialization()};
		jdbctemplate.update(dprofile,add);
		
	}
	public void dupdateProfile(Doctor doctor) {
		logger.info("Doctor update page");
		String profile="update doctorprofiles set doctorname=?,gender=?,age=?,address=?,mailid=?,mobileNo=?,specialization=? where regid=?";
		Object[] update= {doctor.getDoctorName(),doctor.getDoctorGender(),doctor.getDoctorAge(),doctor.getDoctorAddress(),doctor.getDoctorMail(),doctor.getMobileNo(),doctor.getSpecialization(),doctor.getRegisterId()};
		jdbctemplate.update(profile,update);
	}
	
	public List<Patient> listofAppointments(Patient patient){
		Integer doctorid=patient.getDoctorId();
		logger.info("listofappointments");
		String app="select appointmentreqno,appointmentdate,patientid,patientname,doctorid,doctorname,disease,appointmentstatus from appointmentapplications where doctorid=? and appointmentstatus='pendingap'";
		
		return jdbctemplate.query(app, new GetappointmentMapper(),doctorid);
		
	}
	public void viewAppointmentList(Patient patient,Model model) throws JsonProcessingException{
		Integer doctorid=patient.getDoctorId();
		String list="select appointmentreqno,appointmentdate,patientid,patientname,doctorid,doctorname,disease,appointmentstatus from appointmentapplications where doctorid=? and appointmentstatus='pendingap'";
		List<Patient>listview=jdbctemplate.query(list, new GetappointmentMapper(),doctorid);
		List<Map<String,Object>>doclist=new ArrayList<>();
		for (Patient patients : listview) {
			Map<String,Object>docView=new HashMap<>();
			docView.put("regid", patients.getRegistId());
			docView.put("appdate", patients.getDate());
			docView.put("patientsid", patients.getPatientId());
			docView.put("patientname", patients.getPatientName());
			docView.put("doctorid",patients.getDoctorId());
			docView.put("doctorname", patients.getDoctorName());
			docView.put("diseaes", patients.getDiseaes());
			doclist.add(docView);
		}
		ObjectMapper mapper=new ObjectMapper();
		String appList=mapper.writeValueAsString(doclist);
		
		model.addAttribute("listapps",appList);
		logger.info(appList);
		logger.info("list of appointments");
	    
	}
	public Boolean giveappointment(Doctor doctor) throws Appointmentreqvalidation {
		
		logger.info("appointment approved page");
		
		String giveappoint="insert into appointmentdetail1(appointmentreqno,doctorid,patientid,appointmentdate,status,medistatus)values(?,?,?,?,?,'pendingmed')";
		Object[] add= {doctor.getRequestNo(),doctor.getDoctorId(),doctor.getPatientId(),doctor.getAppointmentDate(),doctor.getStatus()};
		jdbctemplate.update(giveappoint,add);
		Integer arno=doctor.getRequestNo();
		String dName=null;
		String log="select appointmentreqno,doctorname,appointmentstatus,appointmentdate from appointmentapplications where appointmentreqno=?";
		List<Doctor> reg=(List<Doctor>) jdbctemplate.query(log, new Appointmentgetmapper(),arno);
		for (Doctor doctorpojo : reg) {
			Integer regno1=doctor.getRequestNo();
			
			if(regno1.equals(arno)) {
			logger.info("appointmentrequest no got it.");
			String pres=doctorpojo.getPrecription();
			dName=doctorpojo.getDoctorName();
			if(pres.equals("pendingap")) {
				String addmedi="update appointmentapplications set appointmentstatus=? where appointmentreqno=?";
				Object[] medicine= {doctor.getStatus(),doctor.getRequestNo()};
				jdbctemplate.update(addmedi, medicine);
				  
			}
			}else
					return false;
			
		}
		doctor.setDoctorName(dName);
		logger.info(doctor.getDoctorName());
		String phistory="insert into patienthistory(patientid,visitdate,visitdoctorid,appointmentstatus,medicine,doctorname)values(?,?,?,?,'pending',?)";
		Object[] his= {doctor.getPatientId(),doctor.getAppointmentDate(),doctor.getDoctorId(),doctor.getStatus(),doctor.getDoctorName()};
		jdbctemplate.update(phistory, his);
		
		String dhistory="insert into doctorhistory(doctorid,checkupdate,checkedpatientid,appointmentstatus,medicine)values(?,?,?,?,'pending')";
		Object[] dhis= {doctor.getDoctorId(),doctor.getAppointmentDate(),doctor.getPatientId(),doctor.getStatus()};
		jdbctemplate.update(dhistory, dhis);
		return true;
	}
	public List<Doctor> listofapproved(Doctor doctor,Model model){
		Integer doctorid=doctor.getDoctorId();
		String approval="select appointmentno,doctorid,patientid,appointmentdate,status from appointmentdetail1 where doctorid=? and medistatus='pendingmed'";
		List<Doctor> app=jdbctemplate.query(approval, new Getapprovalmapper(),doctorid);
		model.addAttribute("name", doctorid);
		return app;
		
	}
	public void listApproved(Doctor doctor,Model model) throws JsonProcessingException {
		Integer doctorid=doctor.getDoctorId();
		String approval="select appointmentno,doctorid,patientid,appointmentdate,status from appointmentdetail1 where doctorid=? and medistatus='pendingmed' and status='approved'";
		List<Doctor> app=jdbctemplate.query(approval, new Getapprovalmapper(),doctorid);
		System.out.println(app+"list");
		List<Map<String,Object>>approvedList=new ArrayList<>();
		for (Doctor doctors : app) {
			Map<String,Object>viewApp=new HashMap<>();
			viewApp.put("appno", doctors.getAppointmentNo());
			viewApp.put("docid", doctors.getDoctorId());
			viewApp.put("pid", doctors.getPatientId());
			viewApp.put("appdate", doctors.getAppointmentDate());
			viewApp.put("status",doctors.getStatus());
			approvedList.add(viewApp);
		}
		System.out.println(approvedList+"approvedlist");
		ObjectMapper mapper=new ObjectMapper();
		String apprList=mapper.writeValueAsString(approvedList);

		model.addAttribute("liststatus",apprList);
		logger.info(apprList);
		logger.info("list of appointments");
	}
	public void addPrecription(Doctor doctor) throws Appointmentreqvalidation,Medicinevalidation {
		logger.info("precription add page");
		String pre="insert into prescriptiondetail(appointmentno,doctorid,patientid,precription,qty,status)values(?,?,?,?,?,'pending')";
		Object[] add= {doctor.getAppointmentNo(),doctor.getDoctorId(),doctor.getPatientId(),doctor.getPrecription(),doctor.getQty()};
		jdbctemplate.update(pre,add);
		Integer ano=doctor.getAppointmentNo();
		String appno="select appointmentno,medistatus from appointmentdetail1 where appointmentno=?";
		List<Doctor> medstatus=(List<Doctor>) jdbctemplate.query(appno, new Medistatusmapper(),ano);
		for (Doctor doctorpojo : medstatus) {
			Integer regno1=doctor.getAppointmentNo();
			
			if(regno1.equals(ano)) {
			logger.info("appointmentnumber no got it.");
			String pres=doctorpojo.getPrecription();
			if(pres.equals("pendingmed")) {
				String addmedi="update appointmentdetail1 set medistatus='approved' where appointmentno=?";
				Object[] medicine= {doctor.getAppointmentNo()};
				jdbctemplate.update(addmedi, medicine);
				  
			}
			}else
					throw new Appointmentreqvalidation();
			
		}

	}
	public void history(Doctor doctor) throws Medicinevalidation {
		Integer patientid=doctor.getPatientId();
		String log="select patientid,medicine from patienthistory where patientid=?";
		List<Doctor> reg=(List<Doctor>) jdbctemplate.query(log, new Historymapper(),patientid);
		for (Doctor doctorpojo : reg) {
			Integer regno1=doctor.getPatientId();
			
			if(regno1.equals(patientid)) {
			logger.info("patient id got it.");
			String pres=doctorpojo.getPrecription();
			if(pres.equals("pending")) {
				String addmedi="update patienthistory set medicine=? where patientid=? and medicine='pending'";
				Object[] medicine= {doctor.getPrecription(),doctor.getPatientId()};
				jdbctemplate.update(addmedi, medicine);
				
				  
			}
			}else
					throw new Medicinevalidation();
			
		}
	}
	public void historyofpatient(Doctor doctor,Admin admin) throws Medicinevalidation {
		Integer doctorid=doctor.getDoctorId();
		Integer did=admin.getAdminId();
		String doctorhistory="select doctorid,medicine from doctorhistory where doctorid=?";
		List<Doctor> getdoctorhis=(List<Doctor>) jdbctemplate.query(doctorhistory, new DoctorHistorymapper(),doctorid);
		for (Doctor doctorpojo : getdoctorhis) {
			Integer regno1=doctor.getDoctorId();
			
			if(regno1.equals(doctorid)) {
			logger.info("doctor id got it.");
			String pres=doctorpojo.getPrecription();
			if(pres.equals("pending")) {
				
				String addmedi="update doctorhistory set medicine=? where doctorid=? and medicine='pending'";
				Object[] medicine= {doctor.getPrecription(),doctor.getDoctorId()};
				jdbctemplate.update(addmedi, medicine);
				
			
			}
			}else
					throw new Medicinevalidation();
			
		}
		String dname="select registrationid,registrationname from registration2 where registrationid=?";
		List<Admin> getdname=(List<Admin>) jdbctemplate.query(dname, new RegisterMapper(),did);
		for (Admin adminpojo : getdname) {
			Integer regno1=admin.getAdminId();
			
			if(regno1.equals(doctorid)) {
			logger.info("doctor id got it.");
			   adminpojo.getName();
				String adddname="update patienthistory set doctorname=? where visitdoctorid=?";
				Object[] getdocname= {admin.getName(),admin.getAdminId()};
				jdbctemplate.update(adddname, getdocname);
			}else
					throw new Medicinevalidation();
			
		}
		
	}
	public List<Doctor> docprofileDetail(Doctor doctor)  {
        logger.info("History login page");
		
		
		Integer doctorid=doctor.getDoctorId();
		String log="select regid,doctorname,gender,age,address,mailid,mobileNo,specialization from doctorprofiles where regid=?";
		 
		return jdbctemplate.query(log, new Doctorprofilrmapper(),doctorid);
	}
	public Integer patientCount() {
        String query1 = "select count(*) from patientprofiles";
      Integer totalCount= jdbctemplate.queryForObject(query1, Integer.class);
      logger.info("patient count");
       return totalCount;
    }
	public Integer approvedCount() {
        String query2 = "select count(*) from appointmentdetail1";
      Integer totalCounts= jdbctemplate.queryForObject(query2, Integer.class);
      logger.info("patient count");
       return totalCounts;
    }

    public List<Patient> getMedicine(Patient patient,Model model){
		String mediName = null;
		Integer hno=patient.getDoctorId();
		String listbill="select sno,medicine from patienthistory where sno=?";
		List<Patient> viewpres=jdbctemplate.query(listbill, new GetMedicine(),hno);
		for (Patient patientpojo : viewpres) {
		
	        mediName = patientpojo.getDiseaes();
	       
				}
		 model.addAttribute("getMedi",mediName);
	        logger.info(mediName);
		return viewpres;
	}
   public void listOfBill(Admin admin,Model model) throws JsonProcessingException{
		Integer patientid=admin.getPatientsId();
		String listbill="select billno,patientid,appointmentno,doctorfees,medicinefees,payamount,paymentstatus from billamounts where patientid=? and paymentstatus='pending'";
		List<Admin>viewbill=jdbctemplate.query(listbill, new BillListmapper(),patientid);
		List<Map<String,Object>>billList=new ArrayList<>();
		for (Admin admins : viewbill) {
			Map<String,Object>billView=new HashMap<>();
			billView.put("billno", admins.getBillNo());
			billView.put("patientid", admins.getPatientsId());
			billView.put("appno", admins.getAppointmentNo());
			billView.put("docfees", admins.getDoctorFees());
			billView.put("medfees",admins.getMedicineFees());
			billView.put("amt", admins.getAmount());
			billView.put("catogories", admins.getCatogories());
			billList.add(billView);
		}
		ObjectMapper mapper=new ObjectMapper();
		String apprList=mapper.writeValueAsString(billList);	
		model.addAttribute("billGet",apprList);
		
	}

 public void billOfCreation(Admin admin,Model model) throws JsonProcessingException{
		String lists="select billno,patientid,appointmentno,doctorfees,medicinefees,payamount,paymentstatus,paiddate from billamounts";
		List<Admin>viewbill=jdbctemplate.query(lists, new BillListmapper());
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
		logger.info(apprList);
			    
	}

	
}
