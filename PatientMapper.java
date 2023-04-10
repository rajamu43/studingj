package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;


public class PatientMapper implements RowMapper<Admin>{

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin=new Admin();
		String mail=rs.getString("mailid");
		admin.setMail(mail);
    
		return admin;
	}

}
