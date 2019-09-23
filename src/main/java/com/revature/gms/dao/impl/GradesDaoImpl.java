package com.revature.gms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.dao.GradesDao;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.Grades;
import com.revature.gms.util.DbUtils;

public class GradesDaoImpl implements GradesDao {
boolean result=false;
Connection connection = null;
PreparedStatement preparedStatement = null;
Grades grades=null;
ResultSet resultSet;
	public boolean updateGrade(Grades grades) {
		try {
			connection = DbUtils.getConnection();
			String sql = "update grades set minmark=?, maxmark=?  where grade=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, grades.getMinMark());
			preparedStatement.setInt(2, grades.getMaxMark());
			preparedStatement.setString(3, grades.getGrade());
			int resultSet = preparedStatement.executeUpdate();
			if(resultSet==1) {
				result=true;
			}
		}
		catch(Exception e) {
			throw new DBException("Unable to update",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement);
		}
		return result;
		
		}
	public List<Grades> viewGrades() {
		List<Grades> gradeList=new ArrayList<Grades>();
		try {	
			connection = DbUtils.getConnection();
			String sql = "select grade,minmark,maxmark from grades";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Grades grades=new Grades();
				grades.setGrade(resultSet.getString("grade"));
				grades.setMinMark(resultSet.getInt("minmark"));
				grades.setMaxMark(resultSet.getInt("maxmark"));	
				gradeList.add(grades);
			}
		}
		catch(Exception e) {
			throw new DBException("Unable to update",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement);
		}
		return gradeList;
	}
	

	public String getGrade(int mark) {
		String grade=null;
		try {
			connection = DbUtils.getConnection();
			String sql = "select grade from grades where ? between minmark and maxmark;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, mark);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				grade=resultSet.getString("grade");
			}
		}
		catch(Exception e) {
			throw new DBException("Unable to update",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement);
		}
		return grade;
	}
	public boolean checkGradeAvailability(char grade) {
		boolean result=false;
		
		try {
			connection = DbUtils.getConnection();
			String sql = "select * from grades where grade=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+grade);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result=true;
			}
		}
		catch(Exception e) {
			throw new DBException("Unable to check grade availability",e);
		}
		finally {
			DbUtils.close(connection, preparedStatement,resultSet);
		}
		return result;
	}

}


