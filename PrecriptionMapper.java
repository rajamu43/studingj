package com.hospital.hospitalmanagement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Doctor;


public class PrecriptionMapper implements RowMapper<Doctor> {

	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Doctor doctor=new Doctor();
		Integer pno=rs.getInt("precriptionno");
		Integer ano=rs.getInt("appointmentno");
		Integer did=rs.getInt("doctorid");
		Integer pid=rs.getInt("patientid");
		String medi=rs.getString("precription");
		Integer qty=rs.getInt("qty");
		doctor.setPrescriptionNo(pno);
		doctor.setAppointmentNo(ano);
		doctor.setDoctorId(did);
		doctor.setPatientId(pid);
		doctor.setPrecription(medi);
		doctor.setQty(qty);
		return doctor;
	}

}
