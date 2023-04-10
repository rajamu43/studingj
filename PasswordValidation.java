package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PasswordValidation extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(PasswordValidation.class);

		logger.info("give password characters&numbers only.not specify any spcial character");
	}

}
