package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Mnovalidation extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Mnovalidation.class);

		logger.info("give 10 digit numbers only");
	}

}
