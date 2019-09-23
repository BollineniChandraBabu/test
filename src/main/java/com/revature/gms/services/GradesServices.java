package com.revature.gms.services;

import java.util.List;
import java.util.Scanner;

import com.revature.gms.dao.impl.GradesDaoImpl;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.loginPage.AdminLogin;
import com.revature.gms.model.Grades;
import com.revature.gms.validator.GradeValidator;

public class GradesServices {
	Scanner scanner=new Scanner(System.in);
	Grades grades=new Grades();
	GradeValidator gradeValidator=new GradeValidator();
	GradesDaoImpl gradeDaoImpl = new GradesDaoImpl();

	boolean result =false;
			public void viewGrades() throws ServiceException {
				List<Grades> gradesList=null;
				try {
					gradesList=gradeDaoImpl.viewGrades();
					System.out.println("Grade\tMinimum Mark\tMaximum Mark");
					for(Grades grades:gradesList) 
					{
						System.out.println(grades.getGrade()+"\t"+grades.getMinMark()+"\t\t"+grades.getMaxMark());
					}
				} catch (DBException e) {
					throw new ServiceException("Unable to view");
					
				}
			}
			public boolean checkGradeAvailability(char grade) throws ServiceException {
				try {
					result=gradeDaoImpl.checkGradeAvailability(grade);
				} catch (DBException e) {
					throw new ServiceException("Unable to view");
				}
				return result;
			}
			public char getGrade() throws ServiceException
			{
				char grade=0;
				while(true) {
				System.out.println("enter one grade to view marks....");
				grade=scanner.next().trim().charAt(0);
				boolean result=gradeDaoImpl.checkGradeAvailability(grade);
				if(result) {break;}
				else
				{
					System.out.println("grade not available....");
					System.out.println("available grades.....\n------------------------------");
					viewGrades();
					System.out.println("------------------------------");
				}
				}
				return grade;
			}
			public void updateGradeRange() throws ServiceException {
				MarksServices marksServices=new MarksServices();
				GradesDaoImpl gradesDaoImpl=new GradesDaoImpl();
				List<Grades> gradesList=gradesDaoImpl.viewGrades();
				int min;
				int max=100;
				for(Grades grades:gradesList) 
			{
				Grades grade=new Grades();
				grade.setGrade(grades.getGrade());
				grade.setMaxMark(max);
				while(true) {
				System.out.println("enter minimum mark for Grade:" +grades.getGrade());
				min=marksServices.getNumber();
				if(max>min && min>=0) 
				{
					max=min-1;
					grade.setMinMark(min);
					break;
				}
				else 
				{
					System.out.println("minimum mark for Garde-"+grades.getGrade() +"should be less than "+max +"and greater than or equal to 0");
				}
				}
				boolean result=gradesDaoImpl.updateGrade(grade);
				if(result==true)
				{
					System.out.println("successfully updated grade marks for grade:"+grade.getGrade());
				}
				else 
				{
					System.out.println("unable to update grade marks");
					
					break;
				}
			}
				AdminLogin adminLogin=new AdminLogin();
				adminLogin.adminLogin();
			}
			
}
