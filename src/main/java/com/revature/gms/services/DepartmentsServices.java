package com.revature.gms.services;

import java.util.List;

import com.revature.gms.dao.impl.DepartmentsDaoImpl;
import com.revature.gms.model.Departments;

public class DepartmentsServices {
DepartmentsDaoImpl departmentsDaoImpl=new DepartmentsDaoImpl();

public boolean checkDepartment(int id) 
{boolean result=false;
result=departmentsDaoImpl.checkDepartment(id);
return result;	
}
public void viewDepartments()
{
List<Departments> departmentsList=departmentsDaoImpl.viewDepartments();
	for(Departments departments:departmentsList)
	{
		System.out.println(departments.getId() +":" +departments.getName());
	}
}


}
