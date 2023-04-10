package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Patient;

public class ProfileMapper implements RowMapper<Patient> {

	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient patient=new Patient();
		Integer rid=rs.getInt("regid");
		String pname=rs.getString("patientname");
		String gen=rs.getString("gender");
		Integer age=rs.getInt("age");
		String address=rs.getString("address");
		String mail=rs.getString("mailid");
		Long mno=rs.getLong("mobileNo");
		String di=rs.getString("disease");
		patient.setPatientId(rid);
		patient.setPatientName(pname);
		patient.setPatientGender(gen);
		patient.setPatientAge(age);
		patient.setPatientAddress(address);
		patient.setPatientMail(mail);
		patient.setMobileNumber(mno);
		patient.setDiseaes(di);
		
		return patient;
	}

}
