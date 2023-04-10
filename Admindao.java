package com.hospital.hospitalmanagement.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hospital.hospitalmanagement.mapper.BillListmapper;
import com.hospital.hospitalmanagement.mapper.Dhistorymapper;
import com.hospital.hospitalmanagement.mapper.GetappointmentMapper;
import com.hospital.hospitalmanagement.mapper.Getapprovalmapper;
import com.hospital.hospitalmanagement.mapper.Getidmapper;
import com.hospital.hospitalmanagement.mapper.Namemapper;
import com.hospital.hospitalmanagement.mapper.PatientMapper;
import com.hospital.hospitalmanagement.mapper.Patienthistorymapper;
import com.hospital.hospitalmanagement.mapper.Paymentmapper;
import com.hospital.hospitalmanagement.mapper.PrecriptionMapper;
import com.hospital.hospitalmanagement.mapper.Precriptionstatusmapper;
import com.hospital.hospitalmanagement.mapper.ProfileMapper;
import com.hospital.hospitalmanagement.mapper.RegisterMapper;
import com.hospital.hospitalmanagement.mapper.Transactionmapper;
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
import com.hospital.hospitalmanagement.validation.Validation;



public class Admindao implements AdminInterface {

	JdbcTemplate jdbctemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger=LoggerFactory.getLogger(Admindao.class);
	
	public void registration(Admin admin,HttpSession session) throws Invalidname  {
		logger.info("Registration page");
		
		String reg="insert into registration2(registrationid,registrationname,password,role,mailid,mobileno) values(seq_reg.nextval,?,?,?,?,?)";
		                                                                                                        //getpayno()
		Object[] registration= {admin.getName(),admin.getPassword(),admin.getCatogories(),admin.getMail(),admin.getMobileNo()};
        logger.info(admin.getName());
		logger.info(admin.getPassword());
		logger.info(admin.getCatogories());
		logger.info(admin.getMail());
		jdbctemplate.update(reg,registration);
		String regcheck="select registrationid,registrationname,password from registration2 where password=?"; 
        String password=admin.getPassword();
        String regname=admin.getName();
        
        List<Admin> getid=(List<Admin>) jdbctemplate.query(regcheck, new Getidmapper(),password);
        for (Admin adminpojo : getid) {
        	String password1=adminpojo.getPassword();
        	if(password1.equals(password)) {
            	String regname1=adminpojo.getName();
            	if(regname1.equals(regname)) {
            		Integer regid1=adminpojo.getRegistrationId();
            		session.setAttribute("regid", regid1);
            		session.setAttribute("regname", regname1);
            		logger.info("registration id:");
            		logger.info("registration name:");
            		
            	}
        	}
        	
			
		}
		logger.info("registration row");
	}
	public Boolean adminLogin(Admin admin,HttpSession session)  {
           logger.info("Admin login page");
		
		Integer regno=admin.getRegistrationId();
		String ppassword=admin.getPassword();
		String log="select registrationid,registrationname,password,role,mailid,mobileno from registration2 where registrationid=?";
		List<Admin> alogin=(List<Admin>) jdbctemplate.query(log, new RegisterMapper(),regno);
	    
		for (Admin adminpojo : alogin) {
			Integer registno=admin.getRegistrationId();
			if(registno.equals(regno)) {
				String patientpassword=adminpojo.getPassword();
				if(patientpassword.equals(ppassword)) {
					String role=adminpojo.getCatogories();
					if(role.equals("admin")) {
						String adminName=adminpojo.getName();
						 String mail=adminpojo.getMail();
						 Long mno=adminpojo.getMobileNo();
						session.setAttribute("regno", registno);
						 session.setAttribute("roles", role);
						 session.setAttribute("aname", adminName);
						 session.setAttribute("adminMail", mail);
						 session.setAttribute("mobNo", mno);
					logger.info("admin login");
					}
					
				}
				else {
					return false;
				}
					
			}
		}
        logger.info("adminlogin page");
		return true;
		
	}
	public void adminAddprofile(Admin admin) {
		logger.info("admin Profile page");
		String aprofile="insert into adminprofiles values(?,?,?,?,?,?,?)";
		Object[] add= {admin.getRegistrationId(),admin.getAdminName(),admin.getGender(),admin.getAge(),admin.getAddress(),admin.getMail(),admin.getMobileNo()};
		jdbctemplate.update(aprofile,add);
		
	}
	public void adminUpdateprofile(Admin admin) {
		logger.info("admin update page");
		String adminprofile="update adminprofile set adminname=? where adminid=?";
		Object[] update= {admin.getAdminName(),admin.getAdminId()};
		jdbctemplate.update(adminprofile,update);
	}

	public void listOfPrescription(Model model) throws JsonProcessingException {
		
		String pre="select precriptionno,appointmentno,doctorid,patientid,precription,qty,status from prescriptiondetail where status='pending'";
		List<Doctor> medi=jdbctemplate.query(pre, new PrecriptionMapper());
		List<Map<String,Object>>preList=new ArrayList<>();
		for (Doctor doctors : medi) {
			Map<String,Object>viewmedicine=new HashMap<>();
			viewmedicine.put("preno", doctors.getPrescriptionNo());
			viewmedicine.put("appointmentNo", doctors.getAppointmentNo());
			viewmedicine.put("doctorid", doctors.getDoctorId());
			viewmedicine.put("patid", doctors.getPatientId());
			viewmedicine.put("medicine",doctors.getPrecription());
			viewmedicine.put("qty",doctors.getQty());
			preList.add(viewmedicine);
		}
		ObjectMapper mapper=new ObjectMapper();
		String appList=mapper.writeValueAsString(preList);
		
		model.addAttribute("listOfbill",appList);		
	}
	public void patientBill(Admin admin) throws Precriptionstatus {
		logger.info("patient bill page");
		String bill="insert into billamounts(patientid,appointmentno,doctorfees,medicinefees,payamount,paymentstatus)values(?,?,?,?,?,'pending')";
		int amt=admin.getDoctorFees()+admin.getMedicineFees();
		int gst=amt+(amt*12)/100;
		admin.setAmount(gst);
		Object[] amount= {admin.getPatientsId(),admin.getAppointmentNo(),admin.getDoctorFees(),admin.getMedicineFees(),admin.getAmount()};
		jdbctemplate.update(bill,amount);
		Integer ano=admin.getAppointmentNo();
		System.out.println(ano+"appointmentno");
		String appno="select appointmentno,status from prescriptiondetail where appointmentno=?";
		List<Admin> medstatus=(List<Admin>) jdbctemplate.query(appno, new Precriptionstatusmapper(),ano);
		System.out.println(medstatus);
		for (Admin adminpojo : medstatus) {
			Integer regno1=admin.getAppointmentNo();
			System.out.println(regno1+"appointmentno itration");
			if(regno1.equals(ano)) {
			logger.info("precriptionstatus no got it.");
			String pres=adminpojo.getAddress();
			System.out.println(pres+"status");
			if(pres.equals("pending")) {
				String addmedi="update prescriptiondetail set status='approved' where appointmentno=?";
				Object[] medicine= {admin.getAppointmentNo()};
				jdbctemplate.update(addmedi, medicine);
				  
			}
			}else
					throw new Precriptionstatus();
			
		}
		
	}

    public void listOfPayment(Model model) throws JsonProcessingException {
		
 		String pay="select receiptno,billno,paymentmode,cardorupino,amount,TRANSACTIONNO,transactiondate from paymentdetails1 where checkstatus='pending'";
		List<Admin> payment=jdbctemplate.query(pay, new Paymentmapper());
		List<Map<String,Object>>payList=new ArrayList<>();
		for (Admin admins : payment) {
			Map<String,Object>viewPayee=new HashMap<>();
			viewPayee.put("regno", admins.getRegistId());
			viewPayee.put("billno", admins.getBillNo());
			viewPayee.put("paymode", admins.getPaymentMode());
			viewPayee.put("payno", admins.getPayno());
			viewPayee.put("amt",admins.getAmount());
			viewPayee.put("transaction",admins.getAppointmentNo());
			viewPayee.put("payDate",admins.getAppointmentDate());
			payList.add(viewPayee);
			}
		
		logger.info("list of pendingpayment");
		ObjectMapper mapper=new ObjectMapper();
		String listPayment;
		listPayment=mapper.writeValueAsString(payList);
		
		model.addAttribute("paymentlist",listPayment);
		logger.info(listPayment);
		
	}
	public void paycheck(Admin admin) throws Transactionpin,Payment {
        logger.info("pay check page");
		
		Integer bno=admin.getBillNo();
		Integer trano=admin.getAppointmentNo();
		Date billDate=admin.getAppointmentDate();
		String paycheck="select receiptno,billno,paymentmode,cardorupino,amount,TRANSACTIONNO,transactiondate from paymentdetails1 where billno=?";
		List<Admin> pcheck=(List<Admin>) jdbctemplate.query(paycheck, new Paymentmapper(),bno);
	    
		for (Admin adminpojo : pcheck) {
			Integer receiptno=adminpojo.getBillNo();
			if(receiptno.equals(bno)) {
				Integer transactionno=adminpojo.getAppointmentNo();
				if(transactionno.equals(trano)) {
					logger.info("paid update");
					String bill="update billamounts set paymentstatus='paid',paiddate=? where billno=?";
					
					Object[] update= {admin.getAppointmentDate(),admin.getBillNo()};
					jdbctemplate.update(bill, update);
					
				}
				else
					throw new Transactionpin();
			}
		}
		String paystatus="select billno,checkstatus from paymentdetails1 where billno=?";
		List<Admin> medstatus=(List<Admin>)jdbctemplate.query(paystatus, new Transactionmapper(),bno);
		for (Admin adminpojo : medstatus) {
			Integer regno1=admin.getBillNo();
			
			if(regno1.equals(bno)) {
			logger.info("payment no got it.");
			String pres=adminpojo.getAddress();
			if(pres.equals("pending")) {
				String addmedi="update paymentdetails1 set checkstatus='approved' where billno=?";
				Object[] medicine= {admin.getBillNo()};
				jdbctemplate.update(addmedi, medicine);
				  
			}
			}else
					throw new Payment();
			
		}
		
     logger.info("paycheck page");
	}
	public Boolean mailValidation(Admin ap,Model model)  {
		logger.info("mailid");
		String mail=ap.getMail();
		String mailvalid = "select mailid from registration2";
		List<Admin> mailList=jdbctemplate.query(mailvalid, new PatientMapper());
		for (Admin adminpojo : mailList) {
			String mailid=adminpojo.getMail();
			if(mailid.equals(mail)) { 
				String mailMeassage="Already mail id registered";
				model.addAttribute("errorMessage6", mailMeassage);
				return false;
			}
			else {
				logger.info("mailid valid");
			}
		}
		return true;
		
	}
	public void billName(Admin admin,Model model) throws Patientname {
        logger.info("Bill name page");
		
		
		Integer patientid=admin.getPatientsId();
		String patientcheck="select regid,patientname from patientprofiles where regid=?";
		List<Admin> billname=(List<Admin>) jdbctemplate.query(patientcheck, new Namemapper(),patientid);
	    
		for (Admin adminpojo : billname) {
			Integer regno1=admin.getPatientsId();
			
			if(regno1.equals(patientid)) {
			logger.info("patient name got it.");
			String pname=adminpojo.getName();
			model.addAttribute("patname", pname);
			  logger.info(pname);
			}else
					throw new Patientname();
			
		}
     logger.info("doctordao page");
	}
	public void historydetail(Doctor doctor,Model model) throws JsonProcessingException {
        logger.info("Patient History page");
		
		Integer patientid=doctor.getPatientId();
		String phistory="select sno,patientid,visitdate,visitdoctorid,doctorname,appointmentstatus,medicine from patienthistory where patientid=?";
		List<Doctor>viewbill=jdbctemplate.query(phistory, new Patienthistorymapper(),patientid);
		List<Map<String,Object>>billList=new ArrayList<>();
		for (Doctor doctors : viewbill) {
			Map<String,Object>billView=new HashMap<>();
			billView.put("sno",doctors.getRegisterId());
			billView.put("patientid", doctors.getPatientId());
			billView.put("appno", doctors.getAppointmentTime());
			billView.put("did", doctors.getDoctorId());
			billView.put("docname",doctors.getDoctorName());
			billView.put("stat", doctors.getStatus());
			billView.put("catogories", doctors.getPrecription());
			billList.add(billView);
		}
		ObjectMapper mapper=new ObjectMapper();
		String apprList=mapper.writeValueAsString(billList);	
		model.addAttribute("patienthistory",apprList);
		logger.info(apprList);
	    
	}
	public void appointmentDetail(Patient patient,Model model) throws JsonProcessingException {
		logger.info("my apppintment page");
		Integer pid=patient.getPatientId();
		String detail="select appointmentreqno,appointmentdate,patientid,patientname,doctorid,doctorname,disease,appointmentstatus from appointmentapplications where patientid=?";
		List<Patient> viewpres=jdbctemplate.query(detail, new GetappointmentMapper(),pid);
		List<Map<String,Object>>appointmentList=new ArrayList<>();
        for (Patient patients : viewpres) {
        	Map<String,Object>statusView=new HashMap<>();
        	statusView.put("sno",patients.getRegistId());
        	statusView.put("appdate", patients.getDate());
        	statusView.put("patientId",patients.getPatientId());
        	statusView.put("patientName",patients.getPatientName());
        	statusView.put("docid", patients.getDoctorId());
        	statusView.put("docname",patients.getDoctorName());
        	statusView.put("stat", patients.getDiseaes());
        	statusView.put("status", patients.getPatientMail());
        	appointmentList.add(statusView);
		}	
        ObjectMapper mapper=new ObjectMapper();
		String listStatus=mapper.writeValueAsString(appointmentList);	
		model.addAttribute("patientappointment",listStatus);
		logger.info(listStatus);
	}
	public List<Doctor> historyOfpdate(Doctor doctor){
		logger.info("Patient view details");
		String appdate=doctor.getAppointmentTime();
		Integer pid=1206;
	
		String visitdate="select sno,patientid,visitdate,visitdoctorid,doctorname,appointmentstatus,medicine from patienthistory where visitdate=? and patientid=?";
		return jdbctemplate.query(visitdate,new Patienthistorymapper(),appdate,pid);
		
	}
	public void doctorhistoryDetail(Doctor doctor,Model model) throws JsonProcessingException  {
        logger.info("Doctor History page");
		Integer doctorid=doctor.getDoctorId();
		String dhistory="select sno,doctorid,checkupdate,checkedpatientid,appointmentstatus,medicine from doctorhistory where doctorid=?";
		List<Doctor>viewHistory=jdbctemplate.query(dhistory, new Dhistorymapper(),doctorid);
		List<Map<String,Object>>myhistory=new ArrayList<>();
		for (Doctor doctorpojo : viewHistory) {
			Map<String,Object>historyView=new HashMap<>();
			historyView.put("sino", doctorpojo.getRegisterId());
			historyView.put("dCode", doctorpojo.getDoctorId());
			historyView.put("checkdate", doctorpojo.getAppointmentTime());
			historyView.put("getid", doctorpojo.getPatientId());
			historyView.put("appStatus", doctorpojo.getStatus());
			historyView.put("viewmedi", doctorpojo.getPrecription());
			myhistory.add(historyView);
		}
		ObjectMapper mapper=new ObjectMapper();
		String doctorHistory=mapper.writeValueAsString(myhistory);
		model.addAttribute("doctorshistory",doctorHistory);
	}
	public Integer doctorCount() {
        String query1 = "select count(*) from doctorprofiles";
      Integer totalCount= jdbctemplate.queryForObject(query1, Integer.class);
      logger.info("doctor count");
       return totalCount;
    }
	public Integer patientCount() {
        String query2 = "select count(*) from patientprofiles";
      Integer totalCount1= jdbctemplate.queryForObject(query2, Integer.class);
      logger.info("patient count");
       return totalCount1;
    }
	public Integer appointmentCount() {
        String query3 = "select count(*) from appointmentapplications";
      Integer totalCount2= jdbctemplate.queryForObject(query3, Integer.class);
      logger.info("appointments count");
       return totalCount2;
    }
	public Integer appointmentCounts(Admin admin) {
		Integer rid=admin.getRegistrationId();
        String query3 = "select count(*) from appointmentapplications where patientid=?";
      Integer totalCount2= jdbctemplate.queryForObject(query3, Integer.class,rid);
      logger.info("appointments count");
       return totalCount2;
    }
	public List<Patient> profileDetail(Patient patient,Model model)  {
		logger.info("Patient profile page");		
		Integer patientid=patient.getPatientId();
		String patientprofile="select regid,patientname,gender,age,address,mailid,mobileNo,disease from patientprofiles where regid=?";
		List<Patient> profiledetail=(List<Patient>) jdbctemplate.query(patientprofile, new ProfileMapper(),patientid);
		model.addAttribute("patient", patientid);
		return profiledetail;
	}
	public List<Doctor> mymedicineDetail(Doctor doctor,Model model) {
		logger.info("My medicine detail page");
		Integer patientid=doctor.getPatientId();
		String medidetail="select precriptionno,appointmentno,doctorid,patientid,precription,qty from prescriptiondetail where patientid=?";
		List<Doctor> mymedi=(List<Doctor>) jdbctemplate.query(medidetail,new PrecriptionMapper(),patientid);
		model.addAttribute("admin", mymedi);
		return mymedi;
	}

}
