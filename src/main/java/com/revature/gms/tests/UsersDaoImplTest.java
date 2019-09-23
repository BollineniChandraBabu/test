package com.revature.gms.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.gms.dao.impl.UsersDaoImpl;
import com.revature.gms.model.Users;

public class UsersDaoImplTest {
	UsersDaoImpl usersDaoImpl=new UsersDaoImpl();
	@Test
	public void testLogin() {
		Users users = usersDaoImpl.login("cs@gmail.com", "cs");
		assertNotNull(users);
	}
	public void testLogin1() {
		Users users = usersDaoImpl.login("cs", "cs");
		assertNull(users);
	}
	public void testLogin2() {
		Users users = usersDaoImpl.login("cs@gmail.com", "csgfgfh");
		assertNull(users);
	}
	
	@Test
	public void testInsert() {
		
	}

	@Test
	public void testCheckByMailId() {
	boolean result = usersDaoImpl.checkByMailId("cs@gmail.com");
	assertTrue(result);
	}
	@Test
	public void testCheckByMailId1() {
	boolean result = usersDaoImpl.checkByMailId("cstytuytuy@gmail.com");
	assertTrue(result);
	}
	@Test
	public void testActivateAccount() {
		usersDaoImpl.activateAccount("dsdfd", 1, "ch@ndra1");
	}

	@Test
	public void testFindById() {
		
	}

	@Test
	public void testFindIdByMail() {
		
	}

}
