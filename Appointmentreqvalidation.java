package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Appointmentreqvalidation extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Appointmentreqvalidation.class);

		logger.info("appointmentrequest not available");
	}

}
