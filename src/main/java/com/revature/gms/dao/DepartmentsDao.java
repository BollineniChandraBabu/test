package com.revature.gms.dao;

import java.util.List;

import com.revature.gms.model.Departments;

public interface DepartmentsDao {
	public List<Departments> viewDepartments();
	public boolean checkDepartment(int id);
	
}
