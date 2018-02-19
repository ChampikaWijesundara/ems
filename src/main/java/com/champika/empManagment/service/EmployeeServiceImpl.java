package com.champika.empManagment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.champika.empManagment.model.Employee;
import com.champika.empManagment.repository.EmployeeRepository;
import com.champika.empManagment.request.CreateEmployeeRequest;
import com.champika.empManagment.request.UpdateEmployeeRequest;
import com.champika.empManagment.response.EmployeeDetailsResponse;

/**
 * Service implementation for employee.
 * 
 * @author Prasad
 *
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * Get employee details for given employee id.
	 * 
	 * @param employeeId
	 * @return empResponse
	 */
	@Override
	public EmployeeDetailsResponse findEmployeeById(String employeeId) {

		EmployeeDetailsResponse empResponse = null;

		// do the repository call for retrieve the employee
		Employee employee = employeeRepository.getEmployeeById(employeeId);

		// convert for response
		if (employee != null) {
			empResponse = new EmployeeDetailsResponse();
			empResponse.setEmployeeId(employee.getEmployeeId());
			empResponse.setFirstName(employee.getFirstName());
			empResponse.setLastName(employee.getLastName());
			empResponse.setDepartmentId(employee.getDepartment().getDepartmentId());
			empResponse.setDepartmentName(employee.getDepartment().getDepartmentName());
		}

		return empResponse;
	}
	
	/**
	 * Get all employee details.
	 * 
	 * @return empResponseList
	 */
	@SuppressWarnings("null")
	@Override
	public List<EmployeeDetailsResponse> findAllEmployees() {

		List<EmployeeDetailsResponse> empResponseList = new ArrayList<>();
		List<Employee> employeeList = employeeRepository.getAllEmployeeList();
		
		if (employeeList != null || !employeeList.isEmpty()) {
			for (Employee employee : employeeList) {

				EmployeeDetailsResponse empResponse = new EmployeeDetailsResponse();

				empResponse.setEmployeeId(employee.getEmployeeId());
				empResponse.setFirstName(employee.getFirstName());
				empResponse.setLastName(employee.getLastName());

				empResponseList.add(empResponse);
			}
		}

		return empResponseList;
	}

	/**
	 * Create new employee.
	 * 
	 * @param employeeRequest
	 * @return boolean
	 */
	@Override
	public boolean addNewEmployee(CreateEmployeeRequest employeeRequest) {

		Employee employee = new Employee();

		employee.setEmployeeId(employeeRequest.getEmployeeId());
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());

		// check whether the employee existence.
		Employee employeeExist = employeeRepository
				.getEmployeeById(employeeRequest.getEmployeeId());
		if (employeeExist == null) {
			employeeRepository.createEmployee(employee);
			return true;
		}
		return false;
	}
	
	/**
	 * Update existing employee.
	 * 
	 * @param employeeId
	 * @param employeeRequest
	 * 
	 * @return boolean
	 */
	@Override
	public boolean updateEmployee(String employeeId,
			UpdateEmployeeRequest employeeRequest) {

		Employee employeeExist = employeeRepository.getEmployeeById(employeeId);

		if (employeeExist != null) {

			Employee employee = new Employee();
			employee.setEmployeeId(employeeRequest.getEmployeeId());
			employee.setFirstName(employeeRequest.getFirstName());
			employee.setLastName(employeeRequest.getLastName());

			employeeRepository.updateEmployee(employeeId, employee);
			return true;
		}
		return false;
	}

	/**
	 * Delete employee for given employee id.
	 * 
	 * @param employeeId
	 * 
	 * @return boolean
	 */
	@Override
	public boolean deleteEmployeeById(String employeeId) {
		Employee employeeExist = employeeRepository.getEmployeeById(employeeId);

		if (employeeExist != null) {
			employeeRepository.deleteEmployee(employeeExist);
			return true;
		}
		return false;
	}
}
