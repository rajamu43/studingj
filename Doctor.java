package com.hospital.hospitalmanagement.model;

import java.sql.Date;

public class Doctor {
	
	private Integer registerId;
	private Integer appointmentNo;
	private Integer requestNo;
	private Integer patientId;
	private Integer doctorId;
	private Integer prescriptionNo;
	private String doctorName;
	private String appointmentTime;
	private Date appointmentDate;
	private String doctorGender;
	private Integer doctorAge;
	private Integer qty;
	private String doctorMail;
	private Long mobileNo;
	private String doctorAddress;
	private String specialization;
	private String status;
	private String precription;
	public Doctor() {
		
	}
	public Doctor(Integer registerId, Integer appointmentNo, Integer requestNo, Integer patientId, Integer doctorId, Integer prescriptionNo,
			String doctorName) {
		super();
		this.registerId = registerId;
		this.appointmentNo = appointmentNo;
		this.requestNo = requestNo;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.prescriptionNo = prescriptionNo;
		this.doctorName = doctorName;
	}
	public Doctor(String appointmentTime,Date appointmentDate, String doctorGender, Integer doctorAge, Integer qty, String doctorMail,
			Long mobileNo) {
		super();
	    this.appointmentTime=appointmentTime;
		this.appointmentDate = appointmentDate;
		this.doctorGender = doctorGender;
		this.doctorAge = doctorAge;
		this.qty = qty;
		this.doctorMail = doctorMail;
		this.mobileNo = mobileNo;
	}
	public Doctor(String doctorAddress, String specialization, String status, String precription) {
		super();
		this.doctorAddress = doctorAddress;
		this.specialization = specialization;
		this.status = status;
		this.precription = precription;
	}

	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrecription() {
		return precription;
	}
	public void setPrecription(String precription) {
		this.precription = precription;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorGender() {
		return doctorGender;
	}
	public void setDoctorGender(String doctorGender) {
		this.doctorGender = doctorGender;
	}
	public Integer getDoctorAge() {
		return doctorAge;
	}
	public void setDoctorAge(Integer doctorAge) {
		this.doctorAge = doctorAge;
	}
	public String getDoctorMail() {
		return doctorMail;
	}
	public void setDoctorMail(String doctorMail) {
		this.doctorMail = doctorMail;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	public Integer getRegisterId() {
		return registerId;
	}
	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}
	public Integer getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(Integer appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public Integer getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(Integer requestNo) {
		this.requestNo = requestNo;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getPrescriptionNo() {
		return prescriptionNo;
	}
	public void setPrescriptionNo(Integer prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	@Override
	public String toString() {
		return "Doctor [registerId=" + registerId + ", appointmentNo=" + appointmentNo + ", requestNo=" + requestNo
				+ ", patientId=" + patientId + ", doctorId=" + doctorId + ", prescriptionNo=" + prescriptionNo
				+ ", doctorName=" + doctorName + ", appointmentTime=" + appointmentTime + ", appointmentDate="
				+ appointmentDate + ", doctorGender=" + doctorGender + ", doctorAge=" + doctorAge + ", qty=" + qty
				+ ", doctorMail=" + doctorMail + ", mobileNo=" + mobileNo + ", doctorAddress=" + doctorAddress
				+ ", specialization=" + specialization + ", status=" + status + ", precription=" + precription + "]";
	}

    

	
	

}
