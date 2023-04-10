package com.hospital.hospitalmanagement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;

public class Dhistorymapper implements RowMapper<Doctor> {

	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor dp=new Doctor();
		Integer sno=rs.getInt("sno");
		Integer did=rs.getInt("doctorid");
		Date vdate=rs.getDate("checkupdate");
		Integer pid=rs.getInt("checkedpatientid");
		String status=rs.getString("appointmentstatus");
		String medi=rs.getString("medicine");
		dp.setRegisterId(sno);
		dp.setDoctorId(did);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		String date = format.format(vdate);
		dp.setAppointmentTime(date);
		dp.setPatientId(pid);
		dp.setStatus(status);
		dp.setPrecription(medi);
		return dp;
	}

}
