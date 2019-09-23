package com.revature.gms.controller;

import java.util.List;

import com.revature.gms.exception.ServiceException;
import com.revature.gms.model.Marks;
import com.revature.gms.model.Users;
import com.revature.gms.services.MarksServices;

public class MarksController {
Marks marks=new Marks();
MarksServices marksServices = new MarksServices();
	public List<Marks> findMaxMarks() throws ServiceException {
		List<Marks> marksList = marksServices.findMaxMarks();
		return marksList;
	}
	public void viewAllMarks() throws ServiceException {
		 marksServices.viewAllMarks();
	
	}
	public void insertOrUpdate(Marks marks) throws ServiceException {
		marksServices.insertOrUpdate(marks);
	}
	public void viewBySubjectCode(int subjectCode) {
		marksServices.viewBySubjectCode(subjectCode);
	
		
	}
	public void viewBySubjectName(String subjectName) {
		marksServices.viewBySubjectName(subjectName);
		
	}
	public void getMarksByGrade(char grade) throws ServiceException {
		marksServices.viewMarksByGrade(grade);
		
	}
	public boolean checkSubjectById(int subjectId) {
		return marksServices.checkSubjectById(subjectId);
	}
	
	

}
