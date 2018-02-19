package com.champika.empManagment.repository;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.champika.empManagment.model.Employee;

@Repository("employeeRepository")
public class EmployeeRepositoryImpl extends AbstractRepo<String, Employee>
		implements EmployeeRepository {
	
	/**
	 * Get Employee details by employeeId.
	 * 
	 * @param employeeId
	 * 
	 * @return
	 */
	@Override
	public Employee getEmployeeById(String employeeId) {
		return getByKey(employeeId);
	}

	/**
	 * Get all employee list.
	 * 
	 * @return employeeList
	 */
	@Override
	public List<Employee> getAllEmployeeList() {
		Query query = getSession().createQuery("from Employee");
		
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = query.list();
		
		return employeeList;
	}

	/**
	 * Create employee.
	 * 
	 * @param employee
	 */
	@Override
	public void createEmployee(Employee employee) {
		persist(employee);
	}

	/**
	 * Update Employee details.
	 * 
	 * @param employeeId
	 * @param employee
	 */
	@Override
	public void updateEmployee(String employeeId, Employee employee) {
		update(employee);

	}
	
	/**
	 * Delete employee.
	 * 
	 * @param employee
	 */
	@Override
	public void deleteEmployee(Employee employee) {
		delete(employee);

	}

}
