package com.revature.gms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.controller.GradesController;
import com.revature.gms.dao.MarksDao;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.Departments;
import com.revature.gms.model.Grades;
import com.revature.gms.model.Marks;
import com.revature.gms.model.Students;
import com.revature.gms.model.Subjects;
import com.revature.gms.util.DbUtils;

public class MarksDaoImpl implements MarksDao {
	Marks marks=null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	GradesController gradesController = new GradesController();
	Grades grades=null;
	List<Marks> marksList;

	public List<Marks> findMaxMarks() {
		int studentId=getMaxAverage();
		return marksList=getMarksById(studentId);
		
	}

	public List<Marks> getMarksById(int id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		List<Marks> marksList=new ArrayList<Marks>();
		try {
			connection = DbUtils.getConnection();
			String sql = "select m.id,m.sid,m.subid,m.marks,s.id,s.name,st.regno,st.name from marks as m, subjects as s,students as st where st.regno=m.sid and m.subid=s.id and st.regno=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Marks marks = new Marks();
				Subjects subjects=new Subjects();
				Students students=new Students();
				students.setRegistrationNumber(resultSet.getInt("st.regno"));
				students.setName(resultSet.getString("st.name"));
				
				subjects.setId(resultSet.getInt("s.id"));
				subjects.setName(resultSet.getString("s.name"));
				marks.setId(resultSet.getInt("m.id"));
				marks.setStudent(students);
				marks.setSubjects(subjects);
				marks.setMarks(resultSet.getInt("m.marks"));
				marksList.add(marks);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return marksList;
	}

	public boolean insertMarks(Marks marks) 
	{
		connection = null;
		preparedStatement = null;
		resultSet = null;
		boolean result=false;
		try {
			connection = DbUtils.getConnection();
			String sql = "insert into marks (sid,subid,marks) values (?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,marks.getStudent().getRegistrationNumber());
			preparedStatement.setInt(2,marks.getSubjects().getId());
			preparedStatement.setInt(3,marks.getMarks());
			int row = preparedStatement.executeUpdate();
			if(row==1) {
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return result;
	}

	public boolean updateMarks(Marks marks) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		boolean result=false;
		try {
			connection = DbUtils.getConnection();
			String sql = "update marks set marks=? where sid=? and subid=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,marks.getMarks());
			preparedStatement.setInt(2,marks.getStudent().getRegistrationNumber());
			preparedStatement.setInt(3,marks.getSubjects().getId());
			int row = preparedStatement.executeUpdate();
			if(row==1) {
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return result;
	}
	
	
	public int getAverage(int id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		int average = 0;
		try {
			connection = DbUtils.getConnection();
			String sql = "select avg(marks) as average from marks where sid=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				average=resultSet.getInt("average");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return average;
	}
	public int getMaxAverage() {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		int studentId = 0;
		try {
			connection = DbUtils.getConnection();
			String sql = "select sid,avg(marks) as average from marks group by sid order by average desc";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				studentId=resultSet.getInt("sid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement,resultSet);
		}
		return studentId;
	}

	public List<Marks> viewBySubjectCode(int subjectCode) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		List<Marks> marksList=new ArrayList<Marks>();
		try {
			connection = DbUtils.getConnection();
			String sql = "select m.subid,m.marks,s.name,st.regno,st.name from marks as m, subjects as s,students as st where s.id=? and st.regno=m.sid and m.subid=s.id group by m.sid,s.name";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,subjectCode);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Marks marks = new Marks();
				Subjects subjects=new Subjects();
				Students students=new Students();
				students.setId(resultSet.getInt("st.regno"));
				students.setName(resultSet.getString("st.name"));
				
				subjects.setId(resultSet.getInt("m.subid"));
				subjects.setName(resultSet.getString("s.name"));
				marks.setStudent(students);
				marks.setSubjects(subjects);
				marks.setMarks(resultSet.getInt("m.marks"));
				marksList.add(marks);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return marksList ;
	}

	public List<Marks> viewBySubjectName(String subjectName) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		List<Marks> marksList=new ArrayList<Marks>();
		try {
			connection = DbUtils.getConnection();
			String sql = "select m.subid,m.marks,s.name,st.regno,st.name from marks as m, subjects as s,students as st where s.name like ? and st.regno=m.sid and m.subid=s.id group by m.sid,s.name";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,subjectName+"%");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Marks marks = new Marks();
				Subjects subjects=new Subjects();
				Students students=new Students();
				students.setId(resultSet.getInt("st.regno"));
				students.setName(resultSet.getString("st.name"));
				
				subjects.setId(resultSet.getInt("m.subid"));
				subjects.setName(resultSet.getString("s.name"));
				marks.setStudent(students);
				marks.setSubjects(subjects);
				marks.setMarks(resultSet.getInt("m.marks"));
				marksList.add(marks);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return marksList ;
	}

	public Marks checkAvailability(int studentId,int subjectId) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		Marks marks=new Marks();
		Students students=new Students();
		Subjects subjects=new Subjects();
		try {
			connection = DbUtils.getConnection();
			String sql = "select st.regno,st.name,s.name,m.marks from marks as m,students as st,subjects as s where m.id=st.regno and m.subid=s.id and st.regno=? and m.subid=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,studentId);
			preparedStatement.setInt(2,subjectId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				students.setRegistrationNumber(resultSet.getInt("st.regno"));
				students.setName(resultSet.getString("st.name"));
				marks.setStudent(students);
				subjects.setName(resultSet.getString("s.name"));
				marks.setSubjects(subjects);
				marks.setMarks(resultSet.getInt("m.marks"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return marks;
	}

	public boolean checkSubjectById(int subjectId) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		boolean result =false;

		try {
			connection = DbUtils.getConnection();
			String sql = "select * from marks where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, subjectId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to view marks", e);
		} finally {
			DbUtils.close(connection, preparedStatement);
		}
		return result;
	}
	
	
}
