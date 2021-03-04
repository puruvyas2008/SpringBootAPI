package com.batch.springboot.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.batch.springboot.model.Employee;

@Component("employeeDAOImpl")
public class EmployeeDAOImpl implements EmployeeDAO {

	public static ArrayList<Employee> em = new ArrayList<Employee>();

	public static int counter = 0;

	static {

		em.add(new Employee(counter++, "Amit", "Kumar", "a2com"));
		em.add(new Employee(counter++, "Ankit", "jhajharia", "b@com"));

		em.add(new Employee(counter++, "Anu", "Poonia", "c@com"));

	}

	@Override
	public List<Employee> getAllEmployees() {
		String url = "jdbc:mysql://localhost:3306/SpringBoot";

		try {
			Connection con = DriverManager.getConnection(url, "root", "aA@9983362919");
			Statement st = con.createStatement();

			int i = st.executeUpdate("insert into customer values(2,'ankit')");
			System.out.println("Added record   " + i);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return em;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		employee.setEmployeeId(counter++);
		em.add(employee);

		return employee;
	}

	@Override
	public Employee updateEmployee(Employee e, int id) {
		Employee e1 = null;

		Iterator<Employee> itr = em.iterator();
		while (itr.hasNext()) {
			e1 = itr.next();
			if (e1.getEmployeeId() == id) {
				itr.remove();

				em.add(e);
				break;
			}

		}

		return e1;
	}

	@Override
	public String deleteEmployee(int id) {
		Employee e1 = null;

		Iterator<Employee> itr = em.iterator();
		while (itr.hasNext()) {
			e1 = itr.next();
			if (e1.getEmployeeId() == id) {
				itr.remove();
				break;
			}

		}
		return "Banda gya";
	}

	@Override
	public Employee getByIdEmployee(int id) {
		Employee e1 = null;
		boolean flag=false;

		Iterator<Employee> itr = em.iterator();
		while (itr.hasNext()) {
			e1= itr.next();
			if (e1.getEmployeeId() == id) {
                flag=true;
				break;
			}

		}
		
		if (flag) {
			return e1;
		}
		else {
		return null;

	}
}
}
