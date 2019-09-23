package com.revature.gms.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.gms.dao.impl.MarksDaoImpl;
import com.revature.gms.model.Marks;
import com.revature.gms.model.Students;
import com.revature.gms.model.Subjects;

public class MarksDaoImplTest {
	MarksDaoImpl marksDaoImpl=new MarksDaoImpl();
	Marks marks=new Marks();
	@Test
	public void testFindMaxMarks() {
		List<Marks> marksList = marksDaoImpl.findMaxMarks();
		assertNotNull(marksList);
	}

	@Test
	public void testGetMarksById() {
		List<Marks> marksList = marksDaoImpl.getMarksById(1);
		assertNotNull(marksList);
	}
	@Test
	public void testGetMarksById1() {
		List<Marks> marksList = marksDaoImpl.getMarksById(100);
		List<Marks> marksList1=new ArrayList<Marks>();
		assertEquals(marksList1,marksList);
		}

	@Test
	public void testInsertMarks() {
		
	}

	@Test
	public void testUpdateMarks() {
		Students students=new Students();
		students.setRegistrationNumber(1);
		marks.setStudent(students);
		Subjects subjects=new Subjects();
		subjects.setId(1);
		marks.setSubjects(subjects);
		marks.setMarks(90);
		
		boolean result = marksDaoImpl.updateMarks(marks);
		assertTrue(result);
	}
	@Test
	public void testUpdateMarks1() {
		Students students=new Students();
		students.setRegistrationNumber(100);
		marks.setStudent(students);
		Subjects subjects=new Subjects();
		subjects.setId(100);
		marks.setSubjects(subjects);
		marks.setMarks(90);
		
		boolean result = marksDaoImpl.updateMarks(marks);
		assertFalse(result);
	}
	@Test
	public void testGetAverage() {
		 int average = marksDaoImpl.getAverage(1);
		 assertEquals(96, average);
	}
	@Test
	public void testGetAverage1() {
		 int average = marksDaoImpl.getAverage(100);
		 assertEquals(0, average);
	}
	
	
	@Test
	public void testGetMaxAverage() {
		int result = marksDaoImpl.getMaxAverage();
		assertEquals(1, result);
	}

	@Test
	public void testViewBySubjectCode() {
		List<Marks> marksList = marksDaoImpl.viewBySubjectCode(1);
		assertNotNull(marksList);
	}
	@Test
	public void testViewBySubjectCode1() {
		List<Marks> marksList = marksDaoImpl.viewBySubjectCode(100);
		List<Marks> marksList1=new ArrayList<Marks>();
		assertEquals(marksList1,marksList);
	}
	
	@Test
	public void testViewBySubjectName() {
		List<Marks> marksList = marksDaoImpl.viewBySubjectName("tel");
		assertNotNull(marksList);
	}
	@Test
	public void testViewBySubjectName1() {
		List<Marks> marksList = marksDaoImpl.viewBySubjectName("gfdgtrtryt");
		List<Marks> marksList1 = new ArrayList<Marks>();
		assertEquals(marksList,marksList1);
	}
	
	
	@Test
	public void testCheckAvailability() {
		Marks marks = marksDaoImpl.checkAvailability(1, 1);
		assertNotNull(marks);
	}
	@Test
	public void testCheckAvailability1() {
		Marks marks = marksDaoImpl.checkAvailability(100, 1);
		assertEquals(0,marks.getId());
	}
	@Test
	public void testCheckAvailability3() {
		Marks marks = marksDaoImpl.checkAvailability(1, 100);
		assertNotNull(marks);
	}
	
	@Test
	public void testCheckSubjectById() {
		boolean result = marksDaoImpl.checkSubjectById(1);
		assertTrue(result);
	}
	@Test
	public void testCheckSubjectById1() {
		boolean result = marksDaoImpl.checkSubjectById(100);
		assertFalse(result);
	}
}
