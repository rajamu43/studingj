package com.hospital.hospitalmanagement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Patient;

public class GetdateMapper implements RowMapper<Patient> {

	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient patient=new Patient();
		Date date1=rs.getDate("appointmentdate");
		Integer pid=rs.getInt("patientid");
		patient.setDate(date1);
		patient.setPatientId(pid);
		return patient;
	}

}
