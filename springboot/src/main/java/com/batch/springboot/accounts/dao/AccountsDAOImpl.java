package com.batch.springboot.accounts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.batch.springboot.accounts.model.Accounts;
@Repository
public class AccountsDAOImpl implements AccountsDAO {
	private String url = "jdbc:mysql://localhost:3306/SpringBoot";
	private String userName = "root";
	private String password = "aA@9983362919";
	@Override
	public List<Accounts> getAllAccounts() {
		List<Accounts> accounts = new ArrayList<>();
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery("select * from accounts");

			while (resultSet.next()) {
				Accounts account = new Accounts();
				account.setAccountNumber(resultSet.getInt(1));
				account.setAccountType(resultSet.getString(2));
				account.setBalance(resultSet.getInt(3));
				accounts.add(account);
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
		return accounts;
	}

	@Override
	public Accounts getAccount(int id) {
		Accounts account = new Accounts();
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery("select * from accounts where accountNumber=" + id);
			while (resultSet.next()) {

				account.setAccountNumber(resultSet.getInt(1));
				account.setAccountType(resultSet.getString(2));
				account.setBalance(resultSet.getInt(3));
				
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
		return account;
	}

	@Override
	public Accounts createAccount(Accounts account) {
		Accounts accounts = new Accounts();
		Connection con = null;
		String sqlQuery = String.format("insert into accounts values(default,'%s','%d')", account.getAccountType(),
				account.getBalance());
		
		
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			int count = st.executeUpdate(sqlQuery);
			if (count > 0) {

				ResultSet resultSet = st.executeQuery(
						"SELECT * FROM accounts WHERE accountNumber=(SELECT MAX(accountNumber) FROM accounts)");
				while (resultSet.next()) {
					accounts.setAccountNumber(resultSet.getInt(1));
					accounts.setAccountType(resultSet.getString(2));
					accounts.setBalance(resultSet.getInt(3));
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
		return accounts;

	}

	@Override
	public Accounts updateAccount(Accounts account, int id) {
		Accounts accounts = new Accounts();

		Connection con = null;
		String sqlQuery = String.format(
				"update accounts set accountType='%s', balance=%d where accountNumber=%d",
				account.getAccountType(), account.getBalance(),  id);

		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			int count = st.executeUpdate(sqlQuery);
			if (count > 0) {
				ResultSet resultSet = st.executeQuery("SELECT * FROM accounts WHERE accountNumber=" + id);
				while (resultSet.next()) {
					accounts.setAccountNumber(resultSet.getInt(1));
					accounts.setAccountType(resultSet.getString(2));
					accounts.setBalance(resultSet.getInt(3));
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
		return accounts;
	}

	@Override
	public Accounts deleteAccount(int id) {
		// TODO Auto-generated method stub
		Accounts accounts = new Accounts();

		Connection con = null;
		String sqlQuery = String.format(
				"delete from accounts where accountNumber=%d", id);
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
				ResultSet resultSet = st.executeQuery("SELECT * FROM accounts WHERE accountNumber=" + id);
				while (resultSet.next()) {
					accounts.setAccountNumber(resultSet.getInt(1));
					accounts.setAccountType(resultSet.getString(2));
					accounts.setBalance(resultSet.getInt(3));
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
		return accounts;
	}

}
