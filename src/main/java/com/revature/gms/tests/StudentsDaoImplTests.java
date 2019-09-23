package com.revature.gms.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.gms.dao.impl.StudentsDaoImpl;
import com.revature.gms.model.Students;

public class StudentsDaoImplTests {
	StudentsDaoImpl studentsDaoImpl=new StudentsDaoImpl();
	@Test
	public void testViewAllStudents() {
		List<Students> studentsList = studentsDaoImpl.viewAllStudents();
		assertNotNull(studentsList);
	}

	@Test
	public void testCheckStudentById() {
		Students students = studentsDaoImpl.checkStudentById(1);
		assertNotNull(students);
	}
	@Test
	public void testCheckStudentById1() {
		Students students = studentsDaoImpl.checkStudentById(100);
		Students students1=new Students();
		assertEquals(students1,students);
	}
}
