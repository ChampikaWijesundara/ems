package com.champika.empManagment.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.champika.empManagment.request.CreateEmployeeRequest;
import com.champika.empManagment.request.UpdateEmployeeRequest;
import com.champika.empManagment.response.EmployeeDetailsResponse;
import com.champika.empManagment.service.EmployeeService;

/**
 * Contains resources related to Employee.
 * 
 * @author Prasad
 *
 */

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * Get all the employee.
	 * 
	 * @return employeeList
	 */
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDetailsResponse>> getAllEmployees() {

		List<EmployeeDetailsResponse> employeeList = employeeService
				.findAllEmployees();

		return new ResponseEntity<List<EmployeeDetailsResponse>>(employeeList,
				HttpStatus.OK);
	}

	/**
	 * Get employee details for given employee id.
	 * 
	 * @param empId
	 * @return employeeDetailsResponse
	 */
	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeDetailsResponse> getEmployeeDetails(
			@PathVariable(value = "id") String empId) {

		EmployeeDetailsResponse employeeDetailsResponse = employeeService
				.findEmployeeById(empId);
		if (employeeDetailsResponse == null) {
			return new ResponseEntity<EmployeeDetailsResponse>(
					employeeDetailsResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EmployeeDetailsResponse>(
				employeeDetailsResponse, HttpStatus.OK);
	}

	/**
	 * Create employee.
	 * 
	 * @param employee
	 * @return
	 */
	@PostMapping("/employee")
	public ResponseEntity<Void> createEmployee(
			@Valid @RequestBody CreateEmployeeRequest employee) {

		boolean status = employeeService.addNewEmployee(employee);

		if (status) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * Update employee details.
	 * 
	 * @param empId
	 * @param employee
	 * @return
	 */
	@PutMapping("/employee/{id}")
	public ResponseEntity<Void> updateEmployee(
			@PathVariable(value = "id") String empId,
			@Valid @RequestBody UpdateEmployeeRequest employee) {

		boolean status = employeeService.updateEmployee(empId, employee);

		if (status) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete Employee for given employee id.
	 * 
	 * @param empId
	 * @return
	 */
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Void> deleteEmployee(
			@PathVariable(value = "id") String empId) {

		boolean status = employeeService.deleteEmployeeById(empId);

		if (status) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}
}
