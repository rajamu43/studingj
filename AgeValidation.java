package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AgeValidation extends Exception{
	
	static{
		Logger logger=LoggerFactory.getLogger(AgeValidation.class);

		logger.info("give age 3 digit positive nos only");
	}


}
