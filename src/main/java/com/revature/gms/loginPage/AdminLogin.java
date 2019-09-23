package com.revature.gms.loginPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.gms.controller.DepartmentsController;
import com.revature.gms.controller.GradesController;
import com.revature.gms.controller.MarksController;
import com.revature.gms.controller.SubjectsController;
import com.revature.gms.controller.UsersController;
import com.revature.gms.dao.impl.DepartmentsDaoImpl;
import com.revature.gms.dao.impl.MarksDaoImpl;
import com.revature.gms.dao.impl.UsersDaoImpl;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.model.Departments;
import com.revature.gms.model.Grades;
import com.revature.gms.model.Marks;
import com.revature.gms.model.Users;
import com.revature.gms.services.GradesServices;
import com.revature.gms.services.MarksServices;
import com.revature.gms.services.UsersServices;
import com.revature.gms.util.Logger;
import com.revature.gms.validator.UsersValidator;

public class AdminLogin {
Scanner scanner=new Scanner(System.in);
boolean result=false;
GradesController gradesController=new GradesController();
UsersController usersController = new UsersController();
MarksController marksController=new MarksController();
MarksServices marksServices=new MarksServices();
UsersServices usersServices=new UsersServices();
Marks marks=new Marks();
Grades grades=new Grades();
UsersValidator usersValidator=new UsersValidator();
	public void adminLogin() throws ServiceException
{
Logger.info("welcome admin..");	
Logger.info("select one service....\n------------------------------\n1.update score range for grade\t2.view top scorer\n3.view all\t\t4.view by particular grade\n5.view scores by subject\t6.view grade range\n7.add new user\t0.log out\n------------------------------");
int choice = 0;
choice=marksServices.getNumber();
switch(choice)
{
case 1:
	{
		gradesController.updateGradeRange();
				break;
				
			}
	
case 2:
	{
		List<Marks> marksList=new ArrayList<Marks>();
		marksList=marksController.findMaxMarks();
		Logger.info("------------------------------------------------------");
		Logger.info("top scorer marks.....");
		for(Marks marks:marksList) {
			Logger.info("id :"+marks.getStudent().getRegistrationNumber());
			Logger.info("Name :"+marks.getStudent().getName());
			break;
		}
		for(Marks marks1:marksList) {
		Logger.info(marks1.getSubjects().getName()+":"+marks1.getMarks());
		}
		Logger.info("------------------------------------------------------");

		adminLogin();
		
		break;
	}
case 3:
	{
		marksController.viewAllMarks();
		adminLogin();
		break;	
	}
case 4:
	{	GradesServices gradeServices =new GradesServices();
		marksController.getMarksByGrade(gradeServices.getGrade());
		adminLogin();
		break;
	}
case 5:
	{
		Logger.info("--------------------------------------------------------------------");
		Logger.info("select one service.....\n1.view marks by subject code\t 2.view marks by subject name");
		while(true)
		{
			choice=marksServices.getNumber();
			if(choice>0 && choice<3) {break;}
			else {Logger.info("select correct choice...");}
		}
		switch(choice)
		{
		case 1:{
			int subjectCode;
			while(true) {
		Logger.info("enter subject code to search.....");	
		subjectCode=marksServices.getNumber();
		SubjectsController subjectsController=new SubjectsController();
		result =subjectsController.checkSubjectCode(subjectCode);
		if(result) { break; }
		else { Logger.info("invalid subject code....");
		subjectsController.viewSubjects();
		}
			}
			marksController.viewBySubjectCode(subjectCode);
		adminLogin();
		break;
		}
		case 2:
		{
			String subjectName;
			while(true) {
		Logger.info("enter subject name to search.....");	
		subjectName=scanner.next();
		SubjectsController subjectsController=new SubjectsController();
		result =subjectsController.checkSubjectName(subjectName);
		if(result) { break; }
		else { Logger.info("invalid subject code....");
		subjectsController.viewSubjects();
		}
			}
			marksController.viewBySubjectName(subjectName);
		adminLogin();
		break;
		}
		}
	}
case 6:
{
gradesController.viewGrades();
	adminLogin();
	break;
}

case 7:
{
	Users users=new Users();
	result=false;
	while(result!=true) {
	Logger.info("enter Name of the student:");
	users.setName(scanner.next());
	result=usersController.validateName(users.getName());
	if(result==false) 
		{
		Logger.error("invalid name....");
		}
	}
	result=false;
	while(result!=true) {
	Logger.info("enter father Name of the student:");
	users.setFatherName(scanner.next());
	result=usersController.validateName(users.getFatherName());
	if(result==false) 
		{
		Logger.error("invalid name....");
		}
	}
	result=false;
	while(true) {
		users.setEmail(usersServices.getEmail());
		result=usersController.checkByMailId(users.getEmail());
		if(result!=true) 
		{
			break;
		}
		else {Logger.error("email already exist....");}
		}
	int id;
	while(true) {
	Logger.info("select department....");
	DepartmentsController departmentsController=new DepartmentsController();
	departmentsController.viewDepartments();
		id=marksServices.getNumber();
	result=departmentsController.checkDepartment(id);
	if(result)
	{
		break;
	}
	else
	{
		Logger.error("enter correct choice....");
	}
	}
	Departments departments=new Departments();
	departments.setId(id);
	users.setDepartment(departments);

	int rows=usersController.insertController(users);
	if(rows!=0) { Logger.info("successfully added"); Logger.info("ID :"+id); }
	else { Logger.error("unable to add new student"); }
	adminLogin();
	break;
}
case 0:
{
Logger.debug("exiting.....");
Index.starter();
break;	
}
default :
{
Logger.error("enter correct choice....");
adminLogin();
break;
}


}


}}

