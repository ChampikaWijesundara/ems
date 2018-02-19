package com.champika.empManagment.request;

/**
 * Request class for department creation.
 * 
 * @author Prasad
 *
 */
public class CreateDepartmentRequest {

	private String departmentId;
	private String departmentName;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
