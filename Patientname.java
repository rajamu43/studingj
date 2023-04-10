package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Patientname extends Exception{
	static{
		Logger logger=LoggerFactory.getLogger(Patientname.class);

		logger.info("patient name not got it");
	}

}
