package com.hospital.hospitalmanagement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;

public class BillListmapper implements RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Admin admin=new Admin();
		Integer bno=rs.getInt("billno");
		Integer pid=rs.getInt("patientid");
		Integer ano=rs.getInt("appointmentno");
		Integer dfees=rs.getInt("doctorfees");
		Integer mfees=rs.getInt("medicinefees");
		Integer pay=rs.getInt("payamount");
		String status=rs.getString("paymentstatus");
		Date billDate=rs.getDate("paiddate");
		admin.setBillNo(bno);
		admin.setPatientsId(pid);
		admin.setAppointmentNo(ano);
		admin.setDoctorFees(dfees);
		admin.setMedicineFees(mfees);
		admin.setAmount(pay);
		admin.setCatogories(status);
		admin.setAppointmentDate(billDate);
		return admin;
	}

}
