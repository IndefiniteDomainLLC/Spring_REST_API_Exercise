package com.globalpayments.rest_api_exercise.repositories;


import com.globalpayments.rest_api_exercise.models.Device;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




/***
 * This interface is responsible for data layer access and inherits lots of methods from JpaRepository
 * 
 * @author bmeeks
 *
 */

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long>  {
	Optional<Device> findDeviceBySerialNumber(String serialNumber);
	Optional<Device> findDeviceByMachineCode(String machineCode);
}