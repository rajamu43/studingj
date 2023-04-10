package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loginvalidation extends Exception{
	static{
		Logger logger=LoggerFactory.getLogger(Loginvalidation.class);

		logger.info("invalid userid&password");
	}


}
