package com.revature.gms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.dao.DepartmentsDao;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.Departments;
import com.revature.gms.model.Users;
import com.revature.gms.util.DbUtils;

public class DepartmentsDaoImpl implements DepartmentsDao{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;
	Users users=null;
public List<Departments> viewDepartments()
{
	List<Departments> departmentsList=new ArrayList<Departments>();
	try {
		connection = DbUtils.getConnection();
		String sql = "select id,name from departments order by id";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			
			Departments departments=new Departments();
			departments.setId(resultSet.getInt("id"));
			departments.setName(resultSet.getString("name"));
			departmentsList.add(departments);
		}
	}
	catch(Exception e) {
		throw new DBException("Unable to login",e);
	}
	finally {
		DbUtils.close(connection, preparedStatement);
	}
	return departmentsList;

}
public boolean checkDepartment(int id)
{boolean result=false;
	try {
		connection = DbUtils.getConnection();
		String sql = "select id,name from departments where id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
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
}
