package com.globalpayments.rest_api_exercise.exceptions;



/***
 * Will encapsulate error responses (message, resource key, and error code) 
 * to be return in JSON
 * 
 * @author bmeeks
 *
 */

class ApiError {

	   private String message;
	   private String resourceKey;
	   private String errorCode;


	   public String getErrorCode() {
		  return errorCode;
	   }

	   public void setErrorCode(String errorCode) {
		  this.errorCode = errorCode;
	   }

	   public String getResourceKey() {
		  return resourceKey;
	   }

	   public void setResourceKey(String resourceKey) {
		  this.resourceKey = resourceKey;
	   }

	   public String getMessage() {
		  return message;
	  }

	  public void setMessage(String message) {
		  this.message = message;
	  }
	
	
}
