package com.revature.gms.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.gms.dao.impl.SubjectsDaoImpl;
import com.revature.gms.model.Subjects;

public class SubjectsDaoImplTest {
	SubjectsDaoImpl subjectsDaoImpl=new SubjectsDaoImpl();
	@Test
	public void testCheckSubjectCode() {
		boolean result = subjectsDaoImpl.checkSubjectCode(1);
		assertTrue(result);
	}
	@Test
	public void testCheckSubjectCode1() {
		boolean result = subjectsDaoImpl.checkSubjectCode(100);
		assertFalse(result);
	}
	
	
	@Test
	public void testViewSubjects() {
		List<Subjects> subjectsList = subjectsDaoImpl.viewSubjects();
		List<Subjects> subjectsList1=new ArrayList<Subjects>();
		assertNotEquals(subjectsList, subjectsList1);
	}

	@Test
	public void testCheckSubjectName() {
		boolean result = subjectsDaoImpl.checkSubjectName("tel");
		assertTrue(result);
	}
	@Test
	public void testCheckSubjectName1() {
		boolean result = subjectsDaoImpl.checkSubjectName("ererggg");
		assertFalse(result);
	}
}
