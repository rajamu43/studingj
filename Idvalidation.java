package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Idvalidation extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Idvalidation.class);

		logger.info("give 4 digit positive numbers only");
	}


}
