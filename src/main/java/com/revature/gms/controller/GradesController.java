package com.revature.gms.controller;

import com.revature.gms.exception.ServiceException;
import com.revature.gms.model.Grades;
import com.revature.gms.services.GradesServices;

public class GradesController {
	boolean result=false;
	GradesServices gradesServices = new GradesServices();
	Grades grades=new Grades();

	
	public void viewGrades() throws ServiceException
	{
		gradesServices.viewGrades();
	}
	public boolean checkGradeAvailability(char grade) throws ServiceException {
		return result=gradesServices.checkGradeAvailability(grade);
	}
	public void updateGradeRange() throws ServiceException {
		gradesServices.updateGradeRange();
	}
		
	}

