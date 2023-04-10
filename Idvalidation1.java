package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Idvalidation1 extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Idvalidation1.class);

		logger.info("Give positive number only");
	}

}
