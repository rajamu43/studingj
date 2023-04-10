package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;

public class Doctorlistmapper implements RowMapper<Doctor> {

	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Doctor doctor=new Doctor();
		Integer id=rs.getInt("regid");
		String dname=rs.getString("doctorname");
		String gen=rs.getString("gender");
		Integer age=rs.getInt("age");
		String address=rs.getString("address");
		String mail=rs.getString("mailid");
		Long mno=rs.getLong("mobileNo");
		String special=rs.getString("specialization");
		doctor.setRegisterId(id);
		doctor.setDoctorName(dname);
		doctor.setDoctorGender(gen);
		doctor.setDoctorAge(age);
		doctor.setDoctorAddress(address);
		doctor.setDoctorMail(mail);
		doctor.setMobileNo(mno);
		doctor.setSpecialization(special);
		return doctor;
	}

}
