package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Payvalidation extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Payvalidation.class);

		logger.info("give payno correctly");
	}

}
