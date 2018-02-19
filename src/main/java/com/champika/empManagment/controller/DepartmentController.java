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

import com.champika.empManagment.request.CreateDepartmentRequest;
import com.champika.empManagment.request.UpdateDepartmentRequest;
import com.champika.empManagment.response.DepartmentDetailsResponse;
import com.champika.empManagment.service.DepartmentService;

/**
 * Contains resources related to Departments.
 * 
 * @author Prasad
 *
 */

@RestController
@RequestMapping("/api/v1/departments/")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	/**
	 * Get all the departments.
	 * 
	 * @return List departmentList
	 */
	@GetMapping("/")
	public ResponseEntity<List<DepartmentDetailsResponse>> getAllDepartments() {

		List<DepartmentDetailsResponse> departmentList = departmentService
				.findAllDepartments();

		return new ResponseEntity<List<DepartmentDetailsResponse>>(
				departmentList, HttpStatus.OK);
	}

	/**
	 * Get Department details for given department Id.
	 * 
	 * @param pidId
	 * @return departmentDetailsResponse
	 */
	@GetMapping("/department/{id}")
	public ResponseEntity<DepartmentDetailsResponse> getDepartmentDetails(
			@PathVariable(value = "id") String pidId) {

		DepartmentDetailsResponse departmentDetailsResponse = departmentService
				.findDepartmentById(pidId);

		return new ResponseEntity<DepartmentDetailsResponse>(
				departmentDetailsResponse, HttpStatus.OK);
	}

	/**
	 * Create new Department.
	 * 
	 * @param department
	 * @return
	 */
	@PostMapping("/department")
	public ResponseEntity<Void> createDepartment(
			@Valid @RequestBody CreateDepartmentRequest department) {

		boolean status = departmentService.addNewDepartment(department);

		if (status) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

	}

	/**
	 * Update Department details.
	 * 
	 * @param dipId
	 * @param department
	 * @return
	 */
	@PutMapping("/department/{id}")
	public ResponseEntity<Void> updateDepartment(
			@PathVariable(value = "id") String dipId,
			@Valid @RequestBody UpdateDepartmentRequest department) {

		boolean status = departmentService.updateDepartment(dipId, department);

		if (status) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Delete department for given department Id.
	 * 
	 * @param dipId
	 * @return
	 */
	@DeleteMapping("/department/{id}")
	public ResponseEntity<Void> deleteDepartment(
			@PathVariable(value = "id") String dipId) {

		boolean status = departmentService.deleteDepartmentById(dipId);

		if (status) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}
}
