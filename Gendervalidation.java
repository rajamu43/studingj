package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Gendervalidation extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Gendervalidation.class);

		logger.info("give input as male or female only");
	}

}
