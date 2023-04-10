package com.hospital.hospitalmanagement.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Invalidname extends Exception {
	static{
		Logger logger=LoggerFactory.getLogger(Invalidname.class);

		logger.info("give name characters only");
	}

}
