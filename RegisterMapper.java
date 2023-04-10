package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;

public class RegisterMapper implements RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Admin admin=new Admin();
		Integer rid=rs.getInt("registrationid");
		admin.setAdminId(rid);
		String roles=rs.getString("role");
		admin.setCatogories(roles);
		String patientpassword=rs.getString("password");
		admin.setPassword(patientpassword);
		String patientname=rs.getString("registrationname");
		admin.setName(patientname);
		String mailId=rs.getString("mailid");
        admin.setMail(mailId);
        Long mobNo=rs.getLong("mobileno");
        admin.setMobileNo(mobNo);
		return admin;
	}
	

}
