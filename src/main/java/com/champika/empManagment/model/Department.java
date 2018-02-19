package com.champika.empManagment.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

	@Id
	@Column(name = "DEPARTMENT_ID")
	private String departmentId;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;
	
	@OneToMany(mappedBy="department")
	private Set<Employee> EmployeeList;

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

	public Set<Employee> getEmployeeList() {
		return EmployeeList;
	}

	public void setEmployeeList(Set<Employee> employeeList) {
		EmployeeList = employeeList;
	}
}
