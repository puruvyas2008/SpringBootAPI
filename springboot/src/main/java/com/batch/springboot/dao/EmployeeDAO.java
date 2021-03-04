package com.batch.springboot.dao;

import java.util.List;

import com.batch.springboot.model.Employee;

public interface EmployeeDAO {
	
	public List<Employee> getAllEmployees();
	
	public Employee addEmployee(Employee employee );
	
	public Employee updateEmployee(Employee e,int id);
	
	public String deleteEmployee(int id);

	public Employee getByIdEmployee(int id);
	

}
