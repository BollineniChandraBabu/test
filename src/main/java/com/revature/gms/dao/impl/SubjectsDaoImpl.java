package com.revature.gms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.dao.SubjectsDao;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.Subjects;
import com.revature.gms.util.DbUtils;

public class SubjectsDaoImpl implements SubjectsDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;
	Subjects subjects=null;
	public boolean checkSubjectCode(int id) {
		boolean result=false;
		try {
			connection = DbUtils.getConnection();
			String sql = "select id,name from subjects where id=?";
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
	public List<Subjects> viewSubjects() {
		List<Subjects> subjectsList=new ArrayList<Subjects>();
		try {
			connection = DbUtils.getConnection();
			String sql = "select id,name from subjects order by id";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				subjects=new Subjects();
				subjects.setId(resultSet.getInt("id"));
				subjects.setName(resultSet.getString("name"));
				subjectsList.add(subjects);
			}
		}
		catch(Exception e) {
			throw new DBException("Unable to login",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement);
		}
		return subjectsList;
	}
	public boolean checkSubjectName(String subjectName) {
		boolean result=false;
		try {
			connection = DbUtils.getConnection();
			String sql = "select id,name from subjects where name like ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subjectName+"%");
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
