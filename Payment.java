package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Payment extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Payment.class);

		logger.info("payment not available");
	}

}
