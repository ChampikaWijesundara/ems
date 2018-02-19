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
import com.champika.empManagment.model.Employee;
import com.champika.empManagment.repository.EmployeeRepository;
import com.champika.empManagment.request.CreateEmployeeRequest;
import com.champika.empManagment.request.UpdateEmployeeRequest;
import com.champika.empManagment.response.EmployeeDetailsResponse;
import com.champika.empManagment.service.EmployeeService;
import com.champika.empManagment.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Before
	public void setUp() {
		
		Employee employee = new Employee();
		employee.setEmployeeId("EM001");
		Department department = new Department();
		department.setDepartmentId("DP001");
		employee.setDepartment(department);
		
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);

		Mockito.when(
				employeeRepository.getEmployeeById(employee
						.getEmployeeId())).thenReturn(employee);
		Mockito.when(
				employeeRepository.getAllEmployeeList()).thenReturn(employeeList);
		Mockito.doNothing().when(employeeRepository).createEmployee(employee);
		Mockito.doNothing().when(employeeRepository).updateEmployee("EM001", employee);
		Mockito.doNothing().when(employeeRepository).deleteEmployee(employee);
	}
	
	@Test
	public void testFindEmployeeById_success() {
		String employeeId = "EM001";
		EmployeeDetailsResponse found = employeeService
				.findEmployeeById(employeeId);
		assertThat(found.getEmployeeId()).isEqualTo(employeeId);
	}
	
	@Test
	public void testFindAllDepartments_success() {
		String employeeId = "EM001";
		List<EmployeeDetailsResponse> employeeResponseList = employeeService
				.findAllEmployees();
		
		assertThat(employeeResponseList.get(0).getEmployeeId()).isEqualTo(employeeId);
	}
	
	@Test
	public void testAddNewDepartment_success() {
		String employeeId = "EM001";
		CreateEmployeeRequest employeeRequest = new CreateEmployeeRequest();
		employeeRequest.setEmployeeId(employeeId);
		employeeRequest.setFirstName("Champika");
		employeeRequest.setLastName("Champika");
		
		boolean employeeResponse = employeeService
				.addNewEmployee(employeeRequest);
		
		assertThat(employeeResponse).isEqualTo(false);//return false because department is exist.
	}

	@Test
	public void testUpdateEmployee_success() {
		String employeeId = "EM001";
		UpdateEmployeeRequest employeeRequest = new UpdateEmployeeRequest();
		employeeRequest.setEmployeeId(employeeId);
		employeeRequest.setFirstName("Prasad");
		
		boolean employeeResponse = employeeService
				.updateEmployee(employeeId,employeeRequest);
		
		assertThat(employeeResponse).isEqualTo(true);
	}
	
	@Test
	public void testDeleteEmployee_success() {
		String employeeId = "EM001";
		
		boolean employeeResponse = employeeService
				.deleteEmployeeById(employeeId);
		
		assertThat(employeeResponse).isEqualTo(true);
	}	
}
