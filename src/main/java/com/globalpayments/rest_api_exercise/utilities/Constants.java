package com.globalpayments.rest_api_exercise.utilities;




public class Constants {

	//TODO should be conventional routes /api/v1/ etc
	
	public static final String baseURL = "/devices";
	public static final String deviceId = "/{id}";
	public static final String serialNumber = "/serialNumber/{serialNumber}";
	public static final String machineCode = "/machineCode/{machineCode}";
	
	//Note: Wasn't sure if letters were allowed (instructions weren't clear) 
	public static final String serialNumberRegEx = "[0-9]+-[0-9]+";

	
}
