package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;

public class Doctorprofilrmapper implements RowMapper<Doctor> {

	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor doctor=new Doctor();
		Integer did=rs.getInt("regid");
		String dname=rs.getString("doctorname");
		String gen=rs.getString("gender");
		Integer age=rs.getInt("age");
		String address=rs.getString("address");
		String mail=rs.getString("mailid");
		Long mno=rs.getLong("mobileNo");
		String di=rs.getString("specialization");
		doctor.setDoctorId(did);
		doctor.setDoctorName(dname);
		doctor.setDoctorGender(gen);
		doctor.setDoctorAge(age);
		doctor.setDoctorAddress(address);
		doctor.setDoctorMail(mail);
		doctor.setMobileNo(mno);
		doctor.setSpecialization(di);
		return doctor;
	}

}
