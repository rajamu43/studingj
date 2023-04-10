package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;

public class Transactionmapper implements RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin=new Admin();
		Integer pid=rs.getInt("billno");
		admin.setAppointmentNo(pid);
		String medi=rs.getString("checkstatus");
		admin.setAddress(medi);
		return admin;
	}

}
