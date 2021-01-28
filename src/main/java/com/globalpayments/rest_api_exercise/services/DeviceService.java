package com.globalpayments.rest_api_exercise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalpayments.rest_api_exercise.exceptions.DeviceNotFoundException;
import com.globalpayments.rest_api_exercise.exceptions.MachineCodeNotFoundException;
import com.globalpayments.rest_api_exercise.exceptions.SerialNumberNotFoundException;
import com.globalpayments.rest_api_exercise.models.Device;
import com.globalpayments.rest_api_exercise.repositories.DeviceRepository;



/***
 * This class is responsible for the business logic and communicating with the data layer
 * @author bmeeks
 */
@Service
public class DeviceService {
	
	
	private final DeviceRepository deviceRepository;
	
	@Autowired //dependency injection
	public DeviceService(DeviceRepository deviceRepository) {
		this.deviceRepository=deviceRepository;
	}
	
	public void createDevice(Device device) {
		//TODO we could also check if the machine code or serial number already exists in database before saving
		this.deviceRepository.save(device);
	}
	
	public List<Device> getAllDevices() {
		return this.deviceRepository.findAll();
	}
	
	//return device resource if found by id, otherwise throw exception
	public Optional<Device> getDeviceById(Long id) {
		Optional<Device> device = deviceRepository.findById(id);
		//could not find device by id
		if(!device.isPresent()) {
			throw new DeviceNotFoundException();
		}
		return device;		
	}
	
	//return device if found by serial number, otherwise throw exception
	public Optional<Device> getDeviceBySerialNumber(String serialNumber) {
		Optional<Device> device = deviceRepository.findDeviceBySerialNumber(serialNumber);
		//could not find device by serial number
		if(!device.isPresent()) {
			throw new SerialNumberNotFoundException();
		}
		return device;		
	}
	
	//return device if found by machine code, otherwise throw exception
	public Optional<Device> getDeviceByMachineCode(String machineCode) {
		Optional<Device> device = deviceRepository.findDeviceByMachineCode(machineCode);
		//could not find device by machine code
		if(!device.isPresent()) {
			throw new MachineCodeNotFoundException();
		}		
		return device;
	}
	
    public void updateDevice(Device device, Long id) {
		Optional<Device> existingDevice = deviceRepository.findById(id);
		if(existingDevice.isPresent()) {
			device.setId(id);
			deviceRepository.save(device);
		}
		else {
			throw new DeviceNotFoundException();
		}
	} 
	
	public void deleteDevice(Long id) {
		//TODO check if id exists before attempting to delete
		Optional<Device> existingDevice = deviceRepository.findById(id);
		if(existingDevice.isPresent()) {
			deviceRepository.deleteById(id);
		}
		else {
			throw new DeviceNotFoundException();
		}
	}
	

}
