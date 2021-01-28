package com.globalpayments.rest_api_exercise.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/***
 * 
 * Helper class to perform basic validations, easy to JUNIT
 * 
 * @author bmeeks3
 *
 */


public class Validator {
	
	public boolean isValidSerialNumber(String serialNumber) {
		if(serialNumber.equals(null)) {
			return false;
		}
		//serial number regular expression to validate format of serial number
		final Pattern p = Pattern.compile(Constants.serialNumberRegEx);
		final Matcher patternMatcher = p.matcher(serialNumber);
		return patternMatcher.matches();
	}
	
	public boolean isValidMachineCode(String machineCode) {
		//check if machine code is blank
		return !machineCode.equals(null) && !machineCode.isEmpty();
	}

}
