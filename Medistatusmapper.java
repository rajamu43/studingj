package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;

public class Medistatusmapper implements RowMapper<Doctor> {

	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor doctor=new Doctor();
		Integer pid=rs.getInt("appointmentno");
		doctor.setAppointmentNo(pid);
		String medi=rs.getString("medistatus");
		doctor.setPrecription(medi);
		return doctor;
	}

}
