package com.hospital.hospitalmanagement.model;

import java.sql.Date;

public class Admin {
	
	  private Integer registrationId;
	  private Integer billNo;
	  private String name;
	  private String password;
	  private String catogories;
	  private Integer registId;
	  private Integer adminId;
	  private Integer patientsId;
	  private Integer appointmentNo;
	  private String adminName;
	  private String gender;
	  private Integer age;
	  private Integer doctorFees;
	  private Integer medicineFees;
	  private Integer amount;
	  private String mail;
      private Long mobileNo;
	  private String address;
	  private String paymentMode;
	  private Long payno;
	  private Date appointmentDate;
		
	  public Admin() {
			
	  }

	public Admin(Integer registrationId, Integer billNo, String name, String password, String catogories, Integer registId,
			Integer adminId) {
		this.registrationId = registrationId;
		this.billNo = billNo;
		this.name = name;
		this.password = password;
		this.catogories = catogories;
		this.registId = registId;
		this.adminId = adminId;
	}
    
	public Admin(Integer patientsId, Integer appointmentNo, String adminName, String gender, Integer age, Integer doctorFees,
			Integer medicineFees) {
		super();
		this.patientsId = patientsId;
		this.appointmentNo = appointmentNo;
		this.adminName = adminName;
		this.gender = gender;
		this.age = age;
		this.doctorFees = doctorFees;
		this.medicineFees = medicineFees;
		
	}
	

	public Admin(Integer amount, String mail, Long mobileNo, String address, String paymentMode, Long payno,Date appointmentDate) {
		super();
		this.amount = amount;
		this.mail = mail;
		this.mobileNo = mobileNo;
		this.address = address;
		this.paymentMode = paymentMode;
		this.payno = payno;
		this.appointmentDate=appointmentDate;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCatogories() {
		return catogories;
	}

	public void setCatogories(String catogories) {
		this.catogories = catogories;
	}

	

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPayno() {
		return payno;
	}

	public void setPayno(long payno) {
		this.payno = payno;
	}

	public Integer getBillNo() {
		return billNo;
	}

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}

	public Integer getRegistId() {
		return registId;
	}

	public void setRegistId(Integer registId) {
		this.registId = registId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
	}

	public Integer getPatientsId() {
		return patientsId;
	}

	public void setPatientsId(Integer patientsId) {
		this.patientsId = patientsId;
	}

	public Integer getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(Integer appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public Integer getDoctorFees() {
		return doctorFees;
	}

	public void setDoctorFees(Integer doctorFees) {
		this.doctorFees = doctorFees;
	}

	public Integer getMedicineFees() {
		return medicineFees;
	}

	public void setMedicineFees(Integer medicineFees) {
		this.medicineFees = medicineFees;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setPayno(Long payno) {
		this.payno = payno;
	}

	@Override
	public String toString() {
		return "Admin [registrationId=" + registrationId + ", billNo=" + billNo + ", name=" + name + ", password="
				+ password + ", catogories=" + catogories + ", registId=" + registId + ", adminId=" + adminId
				+ ", patientsId=" + patientsId + ", appointmentNo=" + appointmentNo + ", adminName=" + adminName
				+ ", gender=" + gender + ", age=" + age + ", doctorFees=" + doctorFees + ", medicineFees="
				+ medicineFees + ", amount=" + amount + ", mail=" + mail + ", mobileNo=" + mobileNo + ", address="
				+ address + ", paymentMode=" + paymentMode + ", payno=" + payno + ", appointmentDate=" + appointmentDate
				+ "]";
	}




		
		

}
