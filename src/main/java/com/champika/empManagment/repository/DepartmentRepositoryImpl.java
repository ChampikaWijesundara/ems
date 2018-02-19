package com.champika.empManagment.repository;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.champika.empManagment.model.Department;

@Repository("departmentRepository")
public class DepartmentRepositoryImpl extends AbstractRepo<String, Department>
		implements DepartmentRepository {

	/**
	 * Get department details by department id.
	 * 
	 * @param departmentId
	 * @return
	 */
	@Override
	public Department getDepartmentById(String departmentId) {
		return getByKey(departmentId);
	
	}

	/**
	 * Get all department details list.
	 * 
	 * @return
	 */
	@Override
	public List<Department> getAllDepartmentList() {
		Query query = getSession().createQuery("from Department");

		@SuppressWarnings("unchecked")
		List<Department> list = query.list();

		return list;
	}

	/**
	 * Create department.
	 * 
	 * @param department
	 */
	@Override
	public void createDepartment(Department department) {
		persist(department);

	}

	/**
	 * Update department details.
	 * 
	 * @param employeeId
	 * @param department
	 */
	@Override
	public void updateDepartment(String employeeId, Department department) {
		update(department);

	}

	/**
	 * Delete department.
	 * 
	 * @param department
	 */
	@Override
	public void deleteDepartment(Department department) {
		delete(department);

	}

}
