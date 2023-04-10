package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;

public class Precriptionstatusmapper implements RowMapper<Admin> {

	
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin=new Admin();
		Integer pid=rs.getInt("appointmentno");
		admin.setAppointmentNo(pid);
		String medi=rs.getString("status");
		admin.setAddress(medi);
		return admin;
	}

}
