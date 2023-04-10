package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;

public class DoctorHistorymapper implements RowMapper<Doctor> {

	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor doctor=new Doctor();
		Integer did=rs.getInt("doctorid");
		doctor.setDoctorId(did);
		String medi=rs.getString("medicine");
		doctor.setPrecription(medi);
		return doctor;
	}

}
