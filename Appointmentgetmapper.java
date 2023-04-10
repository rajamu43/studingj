package com.hospital.hospitalmanagement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;

public class Appointmentgetmapper implements RowMapper<Doctor> {

	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor doctor=new Doctor();
		Integer pid=rs.getInt("appointmentreqno");
		doctor.setRequestNo(pid);
		String doctorName=rs.getString("doctorname");
		doctor.setDoctorName(doctorName);
		String medi=rs.getString("appointmentstatus");
		doctor.setPrecription(medi);
		Date changeDate=rs.getDate("appointmentdate");
		doctor.setAppointmentDate(changeDate);
	
		return doctor;
	}

}
