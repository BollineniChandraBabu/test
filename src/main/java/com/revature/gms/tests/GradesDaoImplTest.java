package com.revature.gms.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.gms.dao.impl.GradesDaoImpl;
import com.revature.gms.model.Grades;

public class GradesDaoImplTest {
Grades grades=new Grades();
GradesDaoImpl gradesDaoImpl=new GradesDaoImpl();
	@Test
	public void testUpdateGrade() {
		grades.setGrade("a");
		grades.setMinMark(85);
		grades.setMaxMark(100);
		boolean result = gradesDaoImpl.updateGrade(grades);
		assertTrue(result);
	}
	@Test
	public void testUpdateGrade1() {
		grades.setGrade("e");
		grades.setMinMark(85);
		grades.setMaxMark(100);
		boolean result = gradesDaoImpl.updateGrade(grades);
		assertFalse(result);
	}
	
	@Test
	public void testViewGrades() {
		List<Grades> gradesList=gradesDaoImpl.viewGrades();
		assertNotNull(gradesList);
	}

	@Test
	public void testGetGrade() {
		String grade = gradesDaoImpl.getGrade(101);
		assertNull(grade);
	}
	public void testGetGrade1() {
		String grade = gradesDaoImpl.getGrade(-101);
		assertNull(grade);
	}
	public void testGetGrade2() {
		String grade = gradesDaoImpl.getGrade(85);
		assertNotNull(grade);
		assertEquals("a", grade);
	}
	public void testGetGrade3() {
		String grade = gradesDaoImpl.getGrade(0);
		assertNotNull(grade);
		assertEquals("f", grade);
	}
	
	@Test
	public void testCheckGradeAvailability() {
		boolean result = gradesDaoImpl.checkGradeAvailability('a');
		assertTrue(result);
	}
	@Test
	public void testCheckGradeAvailability1() {
		boolean result = gradesDaoImpl.checkGradeAvailability('e');
		assertFalse(result);
	}
	@Test
	public void testCheckGradeAvailability3() {
		boolean result = gradesDaoImpl.checkGradeAvailability('1');
		assertFalse(result);
	}
}
