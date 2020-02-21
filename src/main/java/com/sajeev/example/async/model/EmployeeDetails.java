package com.sajeev.example.async.model;

public class EmployeeDetails {

	private EmployeeName empName;
	private EmployeeAddress empAddr;
	private String empPhone;
	public EmployeeName getEmpName() {
		return empName;
	}
	public void setEmpName(EmployeeName empName) {
		this.empName = empName;
	}
	public EmployeeAddress getEmpAddr() {
		return empAddr;
	}
	public void setEmpAddr(EmployeeAddress empAddr) {
		this.empAddr = empAddr;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	
}
