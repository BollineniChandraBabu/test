package com.revature.gms.controller;

import com.revature.gms.services.SubjectsServices;

public class SubjectsController {
boolean result=false;
SubjectsServices subjectsServices=new SubjectsServices();

	public boolean checkSubjectCode(int subjectCode) {
		
		return subjectsServices.checkSubjectCode(subjectCode);
		
	}
	public void viewSubjects() {
		subjectsServices.viewSubjects();
		
	}
	public boolean checkSubjectName(String subjectName) {
		
		return subjectsServices.checkSubjectName(subjectName);
		
	}

}
