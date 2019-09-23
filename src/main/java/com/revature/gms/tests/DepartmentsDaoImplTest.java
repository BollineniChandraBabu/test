package com.revature.gms.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.gms.dao.impl.DepartmentsDaoImpl;
import com.revature.gms.model.Departments;

public class DepartmentsDaoImplTest {
DepartmentsDaoImpl departmentsDaoImpl=new DepartmentsDaoImpl();
	@Test
	public void testCheckDepartment() {
		boolean result = departmentsDaoImpl.checkDepartment(1);
		assertTrue(result);
	}
	@Test
	public void testCheckDepartment1() {
		boolean result = departmentsDaoImpl.checkDepartment(0);
		assertFalse(result);
	}
	@Test
	public void testCheckDepartment2() {
		boolean result = departmentsDaoImpl.checkDepartment(-99);
		assertFalse(result);
	}
	
	
	
	@Test
	public void testViewDepartments() {
		List<Departments> departmentsList=departmentsDaoImpl.viewDepartments();
		assertNotNull(departmentsList);
	}

}
