package com.champika.empManagment.repository;

import java.util.List;

import com.champika.empManagment.model.Department;

/**
 * Department repository interface.
 * 
 * @author Prasad
 *
 */
public interface DepartmentRepository {
	
	/**
	 * Get department details by department id.
	 * 
	 * @param departmentId
	 * @return 
	 */
	public Department getDepartmentById(String departmentId);
	
	/**
	 * Get all department details list.
	 * 
	 * @return
	 */
	public List<Department> getAllDepartmentList();
	
	/**
	 * Create department. 
	 * 
	 * @param department
	 */
	public void createDepartment(Department department);
	
	/**
	 * Update department details.
	 * 
	 * @param employeeId
	 * @param department
	 */
	public void updateDepartment(String employeeId, Department department);
	
	/**
	 * Delete department.
	 * 
	 * @param department
	 */
	public void deleteDepartment(Department department);
}
