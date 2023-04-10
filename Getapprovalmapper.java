package com.hospital.hospitalmanagement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;

public class Getapprovalmapper implements RowMapper<Doctor> {


	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor doctor=new Doctor();
		Integer appno=rs.getInt("appointmentno");
		Integer did=rs.getInt("doctorid");
		Integer pid=rs.getInt("patientid");
		Date appdate=rs.getDate("appointmentdate");
		String status=rs.getString("status");
		doctor.setAppointmentNo(appno);
		
		doctor.setDoctorId(did);
		doctor.setPatientId(pid);
//		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
//		String adate = format.format(appdate);
		doctor.setAppointmentDate(appdate);
//		doctor.setDoctorMail(adate);
		doctor.setStatus(status);
		return doctor;
	}

}
