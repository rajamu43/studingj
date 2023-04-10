package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AddressValidation extends Exception {
	{
		Logger logger=LoggerFactory.getLogger(AddressValidation.class);

		logger.info("give address correctly.");
		logger.info("not specify any special character.ex(@#)");
	}

}
