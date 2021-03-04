package com.batch.springboot.customers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.batch.springboot.customers.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	private String url = "jdbc:mysql://localhost:3306/SpringBoot";
	private String userName = "root";
	private String password = "aA@9983362919";

	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<>();
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery("select * from customers");

			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(resultSet.getInt(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setEmail(resultSet.getString(4));
				customers.add(customer);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return customers;
	}

	@Override
	public Customer getCustomer(int id) {
		Customer customer = new Customer();
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery("select * from customers where customerId=" + id);
			while (resultSet.next()) {

				customer.setCustomerId(resultSet.getInt(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setEmail(resultSet.getString(4));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return customer;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		Customer customer1 = new Customer();

		Connection con = null;
		String sqlQuery = String.format("insert into customers values(default,'%s','%s','%s')", customer.getFirstName(),
				customer.getLastName(), customer.getEmail());
		System.out.println(sqlQuery);
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			int count = st.executeUpdate(sqlQuery);
			if (count > 0) {

				ResultSet resultSet = st.executeQuery(
						"SELECT * FROM customers WHERE customerId=(SELECT MAX(customerId) FROM customers)");
				while (resultSet.next()) {
					customer1.setCustomerId(resultSet.getInt(1));
					customer1.setFirstName(resultSet.getString(2));
					customer1.setLastName(resultSet.getString(3));
					customer1.setEmail(resultSet.getString(4));

				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return customer1;

	}

	@Override
	public Customer updateCustomer(Customer customer, int id) {

		Customer customer1 = new Customer();

		Connection con = null;
		String sqlQuery = String.format(
				"update customers set firstName='%s', lastName='%s', email='%s' where customerId=%d",
				customer.getFirstName(), customer.getLastName(), customer.getEmail(), id);

		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			int count = st.executeUpdate(sqlQuery);
			if (count > 0) {

				ResultSet resultSet = st.executeQuery("SELECT * FROM customers WHERE customerId=" + id);
				while (resultSet.next()) {
					customer1.setCustomerId(resultSet.getInt(1));
					customer1.setFirstName(resultSet.getString(2));
					customer1.setLastName(resultSet.getString(3));
					customer1.setEmail(resultSet.getString(4));

				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return customer1;

	}

	@Override
	public Customer deleteCustomer(int id) {
		Customer customer1 = new Customer();

		Connection con = null;
		String sqlQuery = String.format("delete from customers where customerId=%d", id);

		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();

			ResultSet resultSet = st.executeQuery("SELECT * FROM customers WHERE customerId=" + id);
			while (resultSet.next()) {
				customer1.setCustomerId(resultSet.getInt(1));
				customer1.setFirstName(resultSet.getString(2));
				customer1.setLastName(resultSet.getString(3));
				customer1.setEmail(resultSet.getString(4));

			}

			st.executeUpdate(sqlQuery);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return customer1;

	}

}
