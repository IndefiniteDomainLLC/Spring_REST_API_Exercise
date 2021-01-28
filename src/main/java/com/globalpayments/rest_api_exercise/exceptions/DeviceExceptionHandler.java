package com.globalpayments.rest_api_exercise.exceptions;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice //Tells all @RestControllers to use this class for handling exceptions thrown
public class DeviceExceptionHandler extends ResponseEntityExceptionHandler {
  
  
   @ExceptionHandler(InvalidMachineCodeException.class)
   protected ResponseEntity<ApiError> handleEntityNotFound(
           InvalidMachineCodeException ex) {
       ApiError apiError = new ApiError();
       apiError.setMessage("The machine code is incorrect. Check the Machine code you provided and try again.");
       apiError.setResourceKey("machine.code.invalid");
       apiError.setErrorCode("ER001");
	   return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(InvalidSerialNumberException.class)
   protected ResponseEntity<ApiError> invalidSerialNumber(
		   InvalidSerialNumberException ex) {
       ApiError apiError = new ApiError();
       apiError.setMessage("The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your entry.");
       apiError.setResourceKey("serial.number.invalid");
       apiError.setErrorCode("ER003");
	    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
   }
   
   
   @ExceptionHandler(SerialNumberNotFoundException.class)
   protected ResponseEntity<ApiError> serialNumberNotFound(
		   SerialNumberNotFoundException ex) {
       ApiError apiError = new ApiError();
       apiError.setMessage("The serial number does not match our records.\"");
       apiError.setResourceKey("serial.number.not.found");
       apiError.setErrorCode("ER004");
	    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
   }
   
   
   @ExceptionHandler(MachineCodeNotFoundException.class)
   protected ResponseEntity<ApiError> machineCodeNotFound(
		   MachineCodeNotFoundException ex) {
       ApiError apiError = new ApiError();
       apiError.setMessage("The machine code does not match our records.");
       apiError.setResourceKey("machine.code.not.found");
       apiError.setErrorCode("ER002");
	   return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(DeviceNotFoundException.class)
   protected ResponseEntity<ApiError> deviceNotFound(
		   DeviceNotFoundException ex) {
       ApiError apiError = new ApiError();
       apiError.setMessage("Could not find device by id.");
       apiError.setResourceKey("device.id.not.found");
       apiError.setErrorCode("ER005");
	   return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
   }
   
   
   
}
