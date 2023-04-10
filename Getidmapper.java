package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;

public class Getidmapper implements RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin=new Admin();
		Integer id;
		String name;
		String password;
		name=rs.getString("registrationname");
		admin.setName(name);
		id=rs.getInt("registrationid");
		admin.setRegistrationId(id);
		password=rs.getString("password");
		admin.setPassword(password);
		
		return admin;
	}

}
