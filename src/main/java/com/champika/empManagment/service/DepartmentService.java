package com.champika.empManagment.service;

import java.util.List;

import com.champika.empManagment.request.CreateDepartmentRequest;
import com.champika.empManagment.request.UpdateDepartmentRequest;
import com.champika.empManagment.response.DepartmentDetailsResponse;

public interface DepartmentService {
	
	/**
	 * Get department details for given department id.
	 * 
	 * @param departmentId
	 * @return
	 */
	public DepartmentDetailsResponse findDepartmentById(String departmentId);
	
	/**
	 * Get all the departments.
	 * 
	 * @return
	 */
	public List<DepartmentDetailsResponse> findAllDepartments();

	/**
	 * Create new department.
	 * 
	 * @param departmentRequest
	 * @return
	 */
	public boolean addNewDepartment(CreateDepartmentRequest departmentRequest);
	
	/**
	 * Update existing department.
	 * 
	 * @param departmentId
	 * @param departmentRequest
	 * @return
	 */
	public boolean updateDepartment(String departmentId, UpdateDepartmentRequest departmentRequest);
	
	/**
	 * Delete department by given department id.
	 * 
	 * @param departmentId
	 * @return
	 */
	public boolean deleteDepartmentById(String departmentId);
}
