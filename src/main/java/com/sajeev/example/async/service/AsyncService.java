package com.sajeev.example.async.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sajeev.example.async.model.EmployeeAddress;
import com.sajeev.example.async.model.EmployeeAddresses;
import com.sajeev.example.async.model.EmployeeName;
import com.sajeev.example.async.model.EmployeeNames;
import com.sajeev.example.async.model.EmployeePhone;

@Service
public class AsyncService {

	private static Logger log = LoggerFactory.getLogger(AsyncService.class);

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeNames> getEmployeeName() throws InterruptedException 
	{
		log.info("Fetch Employee Name Starts");
		
		EmployeeNames employeeNames = new EmployeeNames();
		List<EmployeeName> employeeNameList = new ArrayList<EmployeeName>();
		
		EmployeeName empName = new EmployeeName();
		empName.setFirstName("Sajeev");
		empName.setLastName("Sasidharan");
		employeeNameList.add(empName);
		employeeNames = new EmployeeNames();
		
		employeeNames.setEmployeeNameList(employeeNameList);
		
		empName = new EmployeeName();
		empName.setFirstName("Rajeev");
		empName.setLastName("N R");
		employeeNameList.add(empName);
		employeeNames = new EmployeeNames();
		
		employeeNames.setEmployeeNameList(employeeNameList);

		log.info("Employee Names, {}", employeeNames);
		Thread.sleep(1000L);	//Intentional delay
		log.info("Employee Name Data completed");
		return CompletableFuture.completedFuture(employeeNames);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeeAddresses> getEmployeeAddress() throws InterruptedException 
	{
		log.info("Fetching Employee Address Starts");
		EmployeeAddresses employeeAddressData = new EmployeeAddresses();
		List<EmployeeAddress> employeeAddressList = new ArrayList<>();
		EmployeeAddress empAddr = new EmployeeAddress();
		empAddr.setHouseNo("Kalathoor Kizhakkathil");
		empAddr.setStreetNo("Manappally South");
		empAddr.setZipCode("690539");
		employeeAddressList.add(empAddr);
		
		empAddr = new EmployeeAddress();
		empAddr.setHouseNo("Naduvilapurayil");
		empAddr.setStreetNo("Manappally South");
		empAddr.setZipCode("690539");
		employeeAddressList.add(empAddr);
		
		employeeAddressData.setEmployeeAddressList(employeeAddressList);
		log.info("Employee AddressData, {}", employeeAddressData);
		Thread.sleep(1000L);	//Intentional delay
		log.info("Fetching Employee Address Ends");
		return CompletableFuture.completedFuture(employeeAddressData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<EmployeePhone> getEmployeePhone() throws InterruptedException 
	{
		log.info("Fetching EmployeePhone Starts");
		EmployeePhone employeePhoneData = new EmployeePhone();
		List<String> phoneNumbers = new ArrayList<>();
		phoneNumbers.add("9620332220");
		phoneNumbers.add("8831123100");
		employeePhoneData.setPhoneNumbers(phoneNumbers);

		log.info("Employee PhoneData, {}", employeePhoneData);
		Thread.sleep(1000L);	//Intentional delay
		log.info("Fetching Employee PhoneData Completed");
		return CompletableFuture.completedFuture(employeePhoneData);
	}

}
