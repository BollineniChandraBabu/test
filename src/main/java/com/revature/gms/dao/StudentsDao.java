package com.revature.gms.dao;

import java.util.List;

import com.revature.gms.model.Students;

public interface StudentsDao {
	public List<Students> viewAllStudents();
	public Students checkStudentById(int studentId);
	
}
