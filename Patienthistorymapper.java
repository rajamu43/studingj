package com.hospital.hospitalmanagement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;

public class Patienthistorymapper implements RowMapper<Doctor> {

	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor doctor=new Doctor();
		Integer sno=rs.getInt("sno");
		Integer pid=rs.getInt("patientid");
		Date vdate=rs.getDate("visitdate");
		Integer did=rs.getInt("visitdoctorid");
		String dname=rs.getString("doctorname");
		String status=rs.getString("appointmentstatus");
		String medi=rs.getString("medicine");
		doctor.setRegisterId(sno);
		doctor.setPatientId(pid);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		String date = format.format(vdate);
		doctor.setAppointmentTime(date);
		doctor.setDoctorId(did);
		doctor.setDoctorName(dname);
		doctor.setStatus(status);
		doctor.setPrecription(medi);
		return doctor;
	}

}
