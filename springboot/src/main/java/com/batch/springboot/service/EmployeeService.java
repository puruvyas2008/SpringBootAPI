package com.batch.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.batch.springboot.dao.EmployeeDAO;
import com.batch.springboot.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	@Qualifier("employeeDAOImpl")
	EmployeeDAO employeeDAO;

	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	public Employee addEmployee(Employee employee) {

		return employeeDAO.addEmployee(employee);
	}

	public Employee updateEmployee(Employee e, int id) {
		return employeeDAO.updateEmployee(e, id);
	}

	public String deleteEmployee(int id) {
		return employeeDAO.deleteEmployee(id);
	}

	public Employee getByIdEmployee(int id) {

		return employeeDAO.getByIdEmployee(id);

	}

}
