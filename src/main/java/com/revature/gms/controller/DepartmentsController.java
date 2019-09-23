package com.revature.gms.controller;

import com.revature.gms.services.DepartmentsServices;

public class DepartmentsController {
	DepartmentsServices departmentsServices=new DepartmentsServices();
	public void viewDepartments()
	{
		departmentsServices.viewDepartments();
	}
	public boolean checkDepartment(int id)
	{
		return departmentsServices.checkDepartment(id);
	}
}
