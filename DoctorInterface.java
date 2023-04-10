package com.hospital.hospitalmanagement.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.validation.Appointmentreqvalidation;
import com.hospital.hospitalmanagement.validation.Loginvalidation;
import com.hospital.hospitalmanagement.validation.Medicinevalidation;

public interface DoctorInterface {
	public Boolean dlogin(Admin admin,HttpSession session)throws Loginvalidation;
	public void daddProfile(Doctor doctor);
	public void dupdateProfile(Doctor doctor);
	public List<Patient> listofAppointments(Patient patient);
	public Boolean giveappointment(Doctor doctor) throws Appointmentreqvalidation;
	public List<Doctor> listofapproved(Doctor doctor,Model model);
	public void history(Doctor doctor) throws Medicinevalidation;
	public void historyofpatient(Doctor doctor,Admin admin) throws Medicinevalidation;
	public List<Doctor> docprofileDetail(Doctor doctor);
	public void listOfBill(Admin admin,Model model)throws JsonProcessingException;
	public void addPrecription(Doctor doctor) throws Appointmentreqvalidation,Medicinevalidation;
	public Integer patientCount();
	public Integer approvedCount();

}
