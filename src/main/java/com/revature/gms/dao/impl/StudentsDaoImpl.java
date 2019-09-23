package com.revature.gms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.dao.StudentsDao;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.Departments;
import com.revature.gms.model.Students;
import com.revature.gms.util.DbUtils;

public class StudentsDaoImpl implements StudentsDao{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public List<Students> viewAllStudents()
	{
		List<Students> studentsList=new ArrayList<Students>();
		try {
			connection = DbUtils.getConnection();
			String sql = "select s.id,s.regno,s.name,s.fathername,d.id,d.name,s.dateofbirth,s.address,s.active from students as s, departments as d where s.department=d.id order by s.id;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Departments departments=new Departments();
				Students students=new Students();
				students.setId(resultSet.getInt("s.id"));
				students.setName(resultSet.getString("s.name"));
				students.setRegistrationNumber(resultSet.getInt("s.regno"));
				students.setFatherName(resultSet.getString("s.fathername"));
				departments.setId(resultSet.getInt("d.id"));
				departments.setName(resultSet.getString("d.name"));
				students.setDepartment(departments);
				students.setDateOfBirth(resultSet.getDate("s.dateofbirth"));
				students.setAddress(resultSet.getString("s.address"));
				students.setActive(resultSet.getBoolean("s.active"));
				studentsList.add(students);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return studentsList;
		
	}

	public Students checkStudentById(int studentId) {
		Departments departments=new Departments();
		Students students=new Students();
		try {
			connection = DbUtils.getConnection();
			String sql = "select s.id,s.regno,s.name,s.fathername,d.id,d.name,s.dateofbirth,s.address,s.active from students as s, departments as d where s.regno=? and s.department=d.id order by s.id";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				students.setId(resultSet.getInt("s.id"));
				students.setName(resultSet.getString("s.name"));
				students.setRegistrationNumber(resultSet.getInt("s.regno"));
				students.setFatherName(resultSet.getString("s.fathername"));
				departments.setId(resultSet.getInt("d.id"));
				departments.setName(resultSet.getString("d.name"));
				students.setDepartment(departments);
				students.setDateOfBirth(resultSet.getDate("s.dateofbirth"));
				students.setAddress(resultSet.getString("s.address"));
				students.setActive(resultSet.getBoolean("s.active"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return students;
	}
	
}
