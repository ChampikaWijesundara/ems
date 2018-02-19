package com.champika.empManagment.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.champika.empManagment.model.Department;
import com.champika.empManagment.repository.DepartmentRepository;
import com.champika.empManagment.request.CreateDepartmentRequest;
import com.champika.empManagment.request.UpdateDepartmentRequest;
import com.champika.empManagment.response.DepartmentDetailsResponse;
import com.champika.empManagment.service.DepartmentService;
import com.champika.empManagment.service.DepartmentServiceImpl;

@RunWith(SpringRunner.class)
public class DepartmentServiceImplTest {

	@TestConfiguration
	static class DepartmentServiceImplTestContextConfiguration {

		@Bean
		public DepartmentService departmentService() {
			return new DepartmentServiceImpl();
		}
	}

	@Autowired
	private DepartmentService departmentService;

	@MockBean
	private DepartmentRepository departmentRepository;

	@Before
	public void setUp() {
		Department department = new Department();
		department.setDepartmentId("DP001");
		
		List<Department> departmentList = new ArrayList<>();
		departmentList.add(department);

		Mockito.when(
				departmentRepository.getDepartmentById(department
						.getDepartmentId())).thenReturn(department);
		Mockito.when(
				departmentRepository.getAllDepartmentList()).thenReturn(departmentList);
		Mockito.doNothing().when(departmentRepository).createDepartment(department);
		Mockito.doNothing().when(departmentRepository).updateDepartment("DP001", department);
		Mockito.doNothing().when(departmentRepository).deleteDepartment(department);
	}

	@Test
	public void testFindDepartmentById_success() {
		String departmentId = "DP001";
		DepartmentDetailsResponse found = departmentService
				.findDepartmentById(departmentId);
		assertThat(found.getDepartmentId()).isEqualTo(departmentId);
	}
	
	@Test
	public void testFindAllDepartments_success() {
		String departmentId = "DP001";
		List<DepartmentDetailsResponse> departmentResponseList = departmentService
				.findAllDepartments();
		
		assertThat(departmentResponseList.get(0).getDepartmentId()).isEqualTo(departmentId);
	}
	
	@Test
	public void testAddNewDepartment_success() {
		String departmentId = "DP001";
		CreateDepartmentRequest departmentRequest = new CreateDepartmentRequest();
		departmentRequest.setDepartmentId(departmentId);
		departmentRequest.setDepartmentName("Education");
		
		boolean departmentResponse = departmentService
				.addNewDepartment(departmentRequest);
		
		assertThat(departmentResponse).isEqualTo(false);//return false because department is exist.
	}

	@Test
	public void testUpdateDepartment_success() {
		String departmentId = "DP001";
		UpdateDepartmentRequest departmentRequest = new UpdateDepartmentRequest();
		departmentRequest.setDepartmentId(departmentId);
		departmentRequest.setDepartmentName("Forign");
		
		boolean departmentResponse = departmentService
				.updateDepartment(departmentId,departmentRequest);
		
		assertThat(departmentResponse).isEqualTo(true);
	}
	
	@Test
	public void testDeleteDepartment_success() {
		String departmentId = "DP001";
		
		boolean departmentResponse = departmentService
				.deleteDepartmentById(departmentId);
		
		assertThat(departmentResponse).isEqualTo(true);
	}

}
