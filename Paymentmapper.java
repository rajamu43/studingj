package com.hospital.hospitalmanagement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hospital.hospitalmanagement.model.Admin;

public class Paymentmapper implements RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin=new Admin();
		Integer reno=rs.getInt("receiptno");
		Integer bno=rs.getInt("billno");
		String pmode=rs.getString("paymentmode");
		Long payno=rs.getLong("cardorupino");
		Integer amt=rs.getInt("amount");
		Integer tno=rs.getInt("TRANSACTIONNO");
		Date billDate=rs.getDate("transactiondate");
		admin.setRegistId(reno);
		admin.setBillNo(bno);
		admin.setPaymentMode(pmode);
		admin.setPayno(payno);
		admin.setAmount(amt);
		admin.setAppointmentNo(tno);
		admin.setAppointmentDate(billDate);
		return admin;
	}

}
