package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Mailidvalidation extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Mailidvalidation.class);

		logger.info("give mailid is @ symbol");
	}

}
