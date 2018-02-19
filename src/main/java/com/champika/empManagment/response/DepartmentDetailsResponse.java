package com.champika.empManagment.response;
/**
 * Response class for Department details.
 * 
 * @author Prasad
 *
 */
public class DepartmentDetailsResponse {

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
