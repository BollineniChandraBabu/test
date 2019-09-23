package com.revature.gms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.gms.dao.UsersDao;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.Departments;
import com.revature.gms.model.Users;
import com.revature.gms.util.DbUtils;

public class UsersDaoImpl implements UsersDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;
	Users users=null;
public Users login(String email,String password)
	{
	try {
		connection = DbUtils.getConnection();
		String sql = "select u.id,u.name,u.fathername,u.email,u.dateofjoining,u.active,u.activeaccount,u.roles,d.id,d.name from users as u, departments as d where u.department=d.id and email=? and password=? and active=1 and activeaccount=1";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			users = new Users();
			Departments department=new Departments();
			users.setId(resultSet.getInt("u.id"));
			users.setName(resultSet.getString("u.name"));
			users.setFatherName(resultSet.getString("u.fathername"));
			users.setEmail(resultSet.getString("u.email"));
			users.setRoles(resultSet.getBoolean("u.roles"));
			department.setId(resultSet.getInt("d.id"));
			department.setName(resultSet.getString("d.name"));
			users.setDepartment(department);
			users.setDateOfJoining(resultSet.getDate("dateofjoining"));
		}
	}
	catch(Exception e) {
		throw new DBException("Unable to login",e);
	}
	finally {
		DbUtils.close(connection, preparedStatement);
	}
	return users;
	}


public int insert(Users users) {
	int id = 0;
	try {
		connection = DbUtils.getConnection();
		String sql = "insert into users (name,fathername,email,department) values(?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, users.getName());
		preparedStatement.setString(2, users.getFatherName());
		preparedStatement.setString(3, users.getEmail());
		preparedStatement.setInt(4, users.getDepartment().getId());
		int rows = preparedStatement.executeUpdate();
		if(rows==1) {
			id=findIdByMail(users.getEmail());
		}
	}
	catch(Exception e) {
		throw new DBException("Unable to insert",e);
	}
	finally {
		DbUtils.close(connection, preparedStatement);
	}
	return id;
	}


public boolean checkByMailId(String mailId) {
	boolean result=false;
	try {
		connection = DbUtils.getConnection();
		String sql = "select id,name,fathername,email,roles from users where email=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, mailId);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			result=true;
		}
	}
	catch(Exception e) {
		throw new DBException("Unable to login",e);
	}
	finally {
		DbUtils.close(connection, preparedStatement);
	}
	return result;
}


	public boolean activateAccount(String email, int id, String password) {
		boolean result = false;
		try{
			connection = DbUtils.getConnection();
		String sql = "update users set password=? ,activeaccount=1 where id=? and email=?";
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, password);
		preparedStatement.setInt(2, id);
		preparedStatement.setString(3, email);
		
		int rows = preparedStatement.executeUpdate();
		if(rows==1) {
			result = true;
		}
	}
	catch(SQLException e) {
		throw new DBException("Unable to update...",e);
	}
	finally {
		DbUtils.close(connection, preparedStatement);
	}
	return result;
	}


	public Users findById(int id) {
		try {
			connection = DbUtils.getConnection();
			String sql = "select id,name,fathername,email,department,dob,roles from users where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				users = new Users();
				users.setId(resultSet.getInt("id"));
				users.setName(resultSet.getString("name"));
				users.setEmail(resultSet.getString("email"));
				users.setRoles(resultSet.getBoolean("roles"));
			}
		}
		catch(Exception e) {
			throw new DBException("Unable to login",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement);
		}
		return users;
	}
	
	public int findIdByMail(String mailId) {
		int id = 0;
		try {
			connection = DbUtils.getConnection();
			String sql = "select id from users where email=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mailId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id=resultSet.getInt("id");
			}
		}
		catch(Exception e) {
			throw new DBException("Unable to find id by mail",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement,resultSet);
		}
		return id;

}}


