package com.globalpayments.rest_api_exercise.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalpayments.rest_api_exercise.exceptions.DeviceNotFoundException;
import com.globalpayments.rest_api_exercise.exceptions.InvalidMachineCodeException;
import com.globalpayments.rest_api_exercise.exceptions.InvalidSerialNumberException;
import com.globalpayments.rest_api_exercise.exceptions.MachineCodeNotFoundException;
import com.globalpayments.rest_api_exercise.exceptions.SerialNumberNotFoundException;

import com.globalpayments.rest_api_exercise.models.Device;
import com.globalpayments.rest_api_exercise.services.DeviceService;
import com.globalpayments.rest_api_exercise.utilities.Validator;

import com.globalpayments.rest_api_exercise.utilities.Constants;



/***
 * This class will handle web server requests for managing device resources 
 * in local PostgreSQL database
 * 
 * @author bmeeks
 *
 */
@RestController
@RequestMapping
public class DeviceController {
	
	private final DeviceService deviceService;
	
	//dependency injection of DeviceService
	@Autowired
	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	//Endpoint to create new device
	@PostMapping(Constants.baseURL)
	public void createDevice(@RequestBody Device device) throws InvalidSerialNumberException {
		
		//Helper class easy to JUNIT test
		final Validator validator = new Validator(); 
		
		//validate serial number format 
		if(!validator.isValidSerialNumber(device.getSerialNumber())) {
			throw new InvalidSerialNumberException();
		}
		//validate machine code isn't blank
		else if (!validator.isValidMachineCode(device.getMachineCode())) {
			throw new InvalidMachineCodeException();
		}
		//persist new device into database iff valid serial number and machine code was not blank
		else {
			 this.deviceService.createDevice(device);
		}
		
	}
	
	//Endpoint to retrieve all devices from the database
	@GetMapping(Constants.baseURL)
	public ResponseEntity<List<Device>> getDevices() {
	    return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.OK);

	}
	
	//Endpoint to retrieve device by id
    @GetMapping(Constants.baseURL+Constants.deviceId)
    public ResponseEntity<Optional<Device>> getDeviceById(@PathVariable("id") Long id)
			throws DeviceNotFoundException {
		return new ResponseEntity<>(deviceService.getDeviceById(id),HttpStatus.OK);
			
	}
	
	//Endpoint to retrieve device by serial number
	@GetMapping(Constants.baseURL+Constants.serialNumber)
	public ResponseEntity<Optional<Device>> getDeviceBySerialNumber(@PathVariable("serialNumber") String serialNumber)
				throws SerialNumberNotFoundException {
		return new ResponseEntity<>(deviceService.getDeviceBySerialNumber(serialNumber),HttpStatus.OK);
		
	}
	
	//Endpoint to retrieve device by machine code
	@GetMapping(Constants.baseURL+Constants.machineCode)
	public ResponseEntity<Optional<Device>> getDeviceByMachineCode(@PathVariable("machineCode") String machineCode)
				throws MachineCodeNotFoundException {
		return new ResponseEntity<>(deviceService.getDeviceByMachineCode(machineCode),HttpStatus.OK);
		
	}
	
	
	@PutMapping(Constants.baseURL+Constants.deviceId) //add new resource //mapping JSON to Device class
	public void updateDevice(@RequestBody Device device, @PathVariable("id") Long id) throws InvalidMachineCodeException, 
	      InvalidSerialNumberException, DeviceNotFoundException {
		
		//Helper class, easy to JUNIT test
        final Validator validator = new Validator();
		
		//validate serial number format 
		if(!validator.isValidSerialNumber(device.getSerialNumber())) {
			throw new InvalidSerialNumberException();
		}
		//validate machine code isn't blank
		else if (!validator.isValidMachineCode(device.getMachineCode())) {
			throw new InvalidMachineCodeException();
		}
		//persist the updated device in database iff valid serial number and machine code was found
		else {
			 this.deviceService.updateDevice(device,id);
		}
		
	}
	
	//Endpoint to delete device resource by id from database
	@DeleteMapping(Constants.baseURL+Constants.deviceId)
	public void deleteDevice(@PathVariable("id") Long id) {
		deviceService.deleteDevice(id);
	}
	
	
	

}
