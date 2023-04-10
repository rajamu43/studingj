package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;

public class Namemapper implements RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin=new Admin();
		Integer rid=rs.getInt("regid");
		admin.setPatientsId(rid);
		String patientname=rs.getString("patientname");
		admin.setName(patientname);
		
		return admin;
	}

}
