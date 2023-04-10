package com.hospital.hospitalmanagement.model;

import java.sql.Date;
import org.springframework.stereotype.Repository;
@Repository
public class Patient {
	
	private Integer registId;
	private Integer patientId;
	private Integer medicineNo;
	private Integer doctorId;
	private String patientName;
	private String doctorName;
	private String patientGender;
	private Date date;
	private Date appointmentdate;
	private Integer patientAge;
	private String patientMail;
	private Long mobileNumber;
	private String patientAddress;
	private String diseaes;
	
	public Patient() {
	
	}
	public Patient(Integer registId, Integer patientId, Integer medicineNo, Integer doctorId, String patientName, String doctorName,
			String patientGender) {
		super();
		this.registId = registId;
		this.patientId = patientId;
		this.medicineNo = medicineNo;
		this.doctorId = doctorId;
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.patientGender = patientGender;
	}

	public Patient(Date date, Integer patientAge, String patientMail, Long mobileNumber, String patientAddress,
			String diseaes,Date appointmentdate) {
		super();
		this.date = date;
		this.patientAge = patientAge;
		this.patientMail = patientMail;
		this.mobileNumber = mobileNumber;
		this.patientAddress = patientAddress;
		this.diseaes = diseaes;
		this.appointmentdate=appointmentdate;
	}
	

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public String getDiseaes() {
		return diseaes;
	}

	public void setDiseaes(String diseaes) {
		this.diseaes = diseaes;
	}

	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public Integer getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientMail() {
		return patientMail;
	}
	public void setPatientMail(String patientMail) {
		this.patientMail = patientMail;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	
	public Integer getRegistId() {
		return registId;
	}
	public void setRegistId(Integer registId) {
		this.registId = registId;
	}
	public Integer getMedicineNo() {
		return medicineNo;
	}
	public void setMedicineNo(Integer medicineNo) {
		this.medicineNo = medicineNo;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Date getAppointmentdate() {
		return appointmentdate;
	}
	public void setAppointmentdate(Date appointmentdate) {
		this.appointmentdate = appointmentdate;
	}
	@Override
	public String toString() {
		return "PatientPojo [registId=" + registId + ", patientId=" + patientId + ", medicineNo=" + medicineNo
				+ ", doctorId=" + doctorId + ", patientName=" + patientName + ", doctorName=" + doctorName
				+ ", patientGender=" + patientGender + ", date=" + date + ", appointmentdate=" + appointmentdate
				+ ", patientAge=" + patientAge + ", patientMail=" + patientMail + ", mobileNumber=" + mobileNumber
				+ ", patientAddress=" + patientAddress + ", diseaes=" + diseaes + "]";
	}




}