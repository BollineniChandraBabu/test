package com.revature.gms.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.revature.gms.util.DbUtils;

public class DBUtilsTest {
	PreparedStatement preparedStatement=null;
	Connection connection=null;
	ResultSet resultSet=null;
	@Test
	public void testGetConnection() {
		Connection connection=DbUtils.getConnection();
		assertNotNull(connection);
	}
	@Test
	public void testCloseWithNull() {
	DbUtils.close(null, null);
	}
	@Test
	public void testClose() {
	DbUtils.close(connection,preparedStatement,resultSet);
	}
	
}
