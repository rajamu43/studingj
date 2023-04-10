package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transactionpin extends Exception{
	static{
		Logger logger=LoggerFactory.getLogger(Transactionpin.class);

		logger.info("transaction pin null");
	}


}
