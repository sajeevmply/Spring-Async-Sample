package com.sajeev.example.async.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sajeev.example.async.model.EmployeeAddresses;
import com.sajeev.example.async.model.EmployeeDetails;
import com.sajeev.example.async.model.EmployeeNames;
import com.sajeev.example.async.model.EmployeePhone;
import com.sajeev.example.async.service.AsyncService;

@RestController
public class AsyncController {

	private static Logger log = LoggerFactory.getLogger(AsyncController.class);

	private final AsyncService service;
	
	@Autowired
	public AsyncController(AsyncService service) {
		super();
		this.service = service;
	}


	@RequestMapping(value = "/getEmpDtls", method = RequestMethod.GET)
	public List<EmployeeDetails> testAsynch() throws InterruptedException, ExecutionException 
	{
		log.info("Asynch Test Start");

		CompletableFuture<EmployeeAddresses> employeeAddress = service.getEmployeeAddress();
		CompletableFuture<EmployeeNames> employeeNames = service.getEmployeeName();
		CompletableFuture<EmployeePhone> employeePhones = service.getEmployeePhone();

		// Wait until they are all done
		CompletableFuture.allOf(employeeAddress, employeeNames, employeePhones).join();
		
		EmployeeAddresses empAddList = employeeAddress.get();
		EmployeeNames empNameList = employeeNames.get();
		EmployeePhone empPhoneList = employeePhones.get();
		log.info("EmployeeAddress--> {}", empAddList);
		log.info("EmployeeNames--> {}", empNameList);
		log.info("EmployeePhones--> {}", empPhoneList);
		
		List<EmployeeDetails> empDtls = mergeResults(empNameList, empAddList,empPhoneList);
		return empDtls;
		
	}


	private List<EmployeeDetails> mergeResults(EmployeeNames empNameList, EmployeeAddresses empAddList,
			EmployeePhone empPhoneList) {
		List<EmployeeDetails> empList = new ArrayList<>();
		EmployeeDetails empDtl = new EmployeeDetails();
		empDtl.setEmpName(empNameList.getEmployeeNameList().get(0));
		empDtl.setEmpAddr(empAddList.getEmployeeAddressList().get(0));
		empDtl.setEmpPhone(empPhoneList.getPhoneNumbers().get(0));
		empList.add(empDtl);
		
		empDtl = new EmployeeDetails();
		empDtl.setEmpName(empNameList.getEmployeeNameList().get(1));
		empDtl.setEmpAddr(empAddList.getEmployeeAddressList().get(1));
		empDtl.setEmpPhone(empPhoneList.getPhoneNumbers().get(1));
		empList.add(empDtl);
		
		return empList;
	}
}
