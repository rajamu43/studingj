package com.hospital.hospitalmanagement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;
import com.hospital.hospitalmanagement.model.Patient;

public class GetappointmentMapper implements RowMapper<Patient> {

	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient patient=new Patient();
		Integer rid=rs.getInt("appointmentreqno");
		Date date1=rs.getDate("appointmentdate");
		Integer pid=rs.getInt("patientid");
		String pname=rs.getString("patientname");
		Integer did=rs.getInt("doctorid");
		String dname=rs.getString("doctorname");
		String disease=rs.getString("disease");
		String approval=rs.getString("appointmentstatus");
		patient.setRegistId(rid);

		patient.setDate(date1);
		patient.setPatientId(pid);
		patient.setPatientName(pname);
		patient.setDoctorId(did);
		patient.setDoctorName(dname);
		patient.setDiseaes(disease);
		patient.setPatientMail(approval);
		return patient;
	}

}
