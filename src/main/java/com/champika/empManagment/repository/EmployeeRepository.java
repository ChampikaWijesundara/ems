package com.champika.empManagment.repository;

import java.util.List;

import com.champika.empManagment.model.Employee;

public interface EmployeeRepository {
	
	/**
	 * Get Employee details by employeeId.
	 * 
	 * @param employeeId
	 * 
	 * @return
	 */
	public Employee getEmployeeById(String employeeId);
	
	/**
	 * Get all employee list.
	 * 
	 * @return
	 */
	public List<Employee> getAllEmployeeList();
	
	/**
	 * Create employee.
	 * 
	 * @param employee
	 */
	public void createEmployee(Employee employee);
	
	/**
	 * Update Employee details.
	 * 
	 * @param employeeId
	 * @param employee
	 */
	public void updateEmployee(String employeeId, Employee employee);
	
	/**
	 * Delete employee.
	 * 
	 * @param employee
	 */
	public void deleteEmployee(Employee employee);
}
