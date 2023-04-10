package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Medicinevalidation extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Medicinevalidation.class);

		logger.info("patient medicine not fill it");
	}

}
