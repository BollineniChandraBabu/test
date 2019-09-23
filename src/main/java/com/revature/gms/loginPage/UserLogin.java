package com.revature.gms.loginPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.gms.controller.GradesController;
import com.revature.gms.controller.MarksController;
import com.revature.gms.controller.StudentsController;
import com.revature.gms.controller.SubjectsController;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.model.Grades;
import com.revature.gms.model.Marks;
import com.revature.gms.model.Students;
import com.revature.gms.model.Subjects;
import com.revature.gms.model.Users;
import com.revature.gms.services.GradesServices;
import com.revature.gms.services.MarksServices;
import com.revature.gms.util.Logger;

public class UserLogin {
	boolean result = false;
	Marks marks=new Marks();
	MarksController marksController=new MarksController();
	Grades grades=new Grades();
	GradesController gradesController=new GradesController();
	Scanner scanner=new Scanner(System.in);
	MarksServices marksServices=new MarksServices();
	public void userLogin() throws ServiceException {
		Logger.debug("welcome user......");
	}
public void userLogin(Users users) throws ServiceException {
	Logger.info("select one service....\n-----------------------------------------\n1.view top scorer\t2.view all\n3.view by particular grade\n4.view scores by subject\n5.view grade range\t6.insert/update your marks\t0.log out\n-----------------------------------------");

	int choice=marksServices.getNumber();
	switch(choice)
	{
	case 1:
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
			userLogin(users);
			break;
		}
	case 2:
		{
			marksController.viewAllMarks();
			userLogin(users);
			break;	
		}
	case 3:
		{
			GradesServices gradeServices =new GradesServices();
			marksController.getMarksByGrade(gradeServices.getGrade());
			userLogin(users);
			break;
		}
	case 4:
		{
			Logger.info("--------------------------------------------------------------------");
			Logger.info("select one service.....\n1.view marks by subject code\t 2.view marks by subject name");
			while(true)
			{
				choice=marksServices.getNumber();
				if(choice>0 && choice<3) {break;}
				else {Logger.error("select correct choice...");}
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
			else { Logger.error("invalid subject code....");
			subjectsController.viewSubjects();
			}
				}
				marksController.viewBySubjectCode(subjectCode);
			userLogin(users);
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
			else { Logger.error("invalid subject code....");
			subjectsController.viewSubjects();
			}
				}
				marksController.viewBySubjectName(subjectName);
				userLogin(users);
				break;
			}
			}
		}
	case 5:
	{
		gradesController.viewGrades();
		userLogin(users);
		break;
	}
	case 6:
	{
		while(true) 
		{
			Logger.info("enter student Id:");
			int studentId=marksServices.getNumber();
			Students students=new Students();
			StudentsController studentsController=new StudentsController();
			students=studentsController.checkStudentById(studentId);
			if(students.getRegistrationNumber()!=0)
			{
				students.setRegistrationNumber(studentId);
			marks.setStudent(students);
				break;
			}
			else
			{
				Logger.error("student id doesnot exist");
			}
		}
		while(true) 
		{
			Logger.info("enter subject id:");
			int subjectId=marksServices.getNumber();
			MarksController marksController=new MarksController();
			Subjects subjects =new Subjects();
			result=marksController.checkSubjectById(subjectId);
			if(result)
			{
				subjects.setId(subjectId);
			marks.setSubjects(subjects);
				break;
			}
			else
			{
				Logger.error("subject Id doesnot exist");
			}
		}
		while(true) 
		{
			Logger.info("enter marks..");
			int mark=marksServices.getNumber();
			
			if(mark<=100 && mark>=0)
			{
				marks.setMarks(mark);
				break;
			}
			else
			{
				Logger.error("marks should be >=0 and <=100");
			}
		}
		marksController.insertOrUpdate(marks);
		break;
	}
	
	case 0:
	{
	Logger.debug("exiting.....");
	Login login=new Login();
	login.login();
	break;	
	}
	default :
	{
	Logger.error("enter correct choice....");
	userLogin(users);
	break;
	}
	

	}



	}
}
