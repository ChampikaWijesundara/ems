package com.champika.empManagment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.champika.empManagment.model.Department;
import com.champika.empManagment.repository.DepartmentRepository;
import com.champika.empManagment.request.CreateDepartmentRequest;
import com.champika.empManagment.request.UpdateDepartmentRequest;
import com.champika.empManagment.response.DepartmentDetailsResponse;
/**
 * Service implementations for department.
 * 
 * @author Prasad
 *
 */
@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	/**
	 * Get department details for given department id.
	 * 
	 * @param departmentId
	 * @return departmentResponse
	 */
	@Override
	public DepartmentDetailsResponse findDepartmentById(String departmentId) {

		DepartmentDetailsResponse departmentResponse = new DepartmentDetailsResponse();

		// do the repository call for retrieve the department
		Department department = departmentRepository
				.getDepartmentById(departmentId);
		if (department != null) {
			// convert for response
			departmentResponse.setDepartmentId(department.getDepartmentId());
			departmentResponse
					.setDepartmentName(department.getDepartmentName());

		}

		return departmentResponse;
	}

	/**
	 * Get all the departments.
	 * 
	 * @return departmentResponseList
	 */
	@Override
	public List<DepartmentDetailsResponse> findAllDepartments() {

		List<DepartmentDetailsResponse> departmentResponseList = new ArrayList<>();
		List<Department> departmentList = departmentRepository
				.getAllDepartmentList();

		for (Department department : departmentList) {

			DepartmentDetailsResponse departmentResponse = new DepartmentDetailsResponse();

			departmentResponse.setDepartmentId(department.getDepartmentId());
			departmentResponse
					.setDepartmentName(department.getDepartmentName());

			departmentResponseList.add(departmentResponse);
		}
		return departmentResponseList;
	}

	/**
	 * Create new department.
	 * 
	 * @param departmentRequest
	 * @return
	 */
	@Override
	public boolean addNewDepartment(CreateDepartmentRequest departmentRequest) {
		Department department = new Department();

		department.setDepartmentId(departmentRequest.getDepartmentId());
		department.setDepartmentName(departmentRequest.getDepartmentName());

		// check whether the department existence.
		Department departmentExist = departmentRepository
				.getDepartmentById(departmentRequest.getDepartmentId());
		if (departmentExist == null) {
			departmentRepository.createDepartment(department);
			return true;
		}
		return false;
	}

	/**
	 * Update existing department.
	 * 
	 * @param departmentId
	 * @param departmentRequest
	 * @return
	 */
	@Override
	public boolean updateDepartment(String departmentId,
			UpdateDepartmentRequest departmentRequest) {

		Department departmentExist = departmentRepository
				.getDepartmentById(departmentId);

		if (departmentExist != null) {

			Department department = new Department();
			department.setDepartmentId(departmentRequest.getDepartmentId());
			department.setDepartmentName(departmentRequest.getDepartmentName());
			departmentRepository.updateDepartment(departmentId, department);
			return true;
		}
		return false;
	}

	/**
	 * Delete department by given department id.
	 * 
	 * @param departmentId
	 * @return boolean
	 */
	@Override
	public boolean deleteDepartmentById(String departmentId) {
		Department departmentExist = departmentRepository
				.getDepartmentById(departmentId);

		if (departmentExist != null) {
			departmentRepository.deleteDepartment(departmentExist);
			return true;
		}
		return false;
	}
}
