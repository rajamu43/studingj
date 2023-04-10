package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Patient;

public class GetMedicine implements RowMapper<Patient> {

	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient patient=new Patient();
		Integer sno=rs.getInt("sno");
		patient.setDoctorId(sno);
		String medicineName=rs.getString("medicine");	
		patient.setDiseaes(medicineName);
		return patient;
	
	}

}
