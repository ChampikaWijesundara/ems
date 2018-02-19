package com.champika.empManagment.service;

import java.util.List;

import com.champika.empManagment.request.CreateEmployeeRequest;
import com.champika.empManagment.request.UpdateEmployeeRequest;
import com.champika.empManagment.response.EmployeeDetailsResponse;

public interface EmployeeService {
	
	/**
	 * Get employee details for given employee id.
	 * 
	 * @param employeeId
	 * @return
	 */
	public EmployeeDetailsResponse findEmployeeById(String employeeId);
	
	/**
	 * Get all employee details.
	 * 
	 * @return
	 */
	public List<EmployeeDetailsResponse> findAllEmployees();

	/**
	 * Create new employee.
	 * 
	 * @param employeeRequest
	 * @return
	 */
	public boolean addNewEmployee(CreateEmployeeRequest employeeRequest);
	
	/**
	 * Update existing employee.
	 * 
	 * @param employeeId
	 * @param employeeRequest
	 * @return
	 */
	public boolean updateEmployee(String employeeId, UpdateEmployeeRequest employeeRequest);
	
	/**
	 * Delete employee for given employee id.
	 * 
	 * @param employeeId
	 * @return
	 */
	public boolean deleteEmployeeById(String employeeId);
}
