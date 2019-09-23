package com.revature.gms.services;

import java.util.List;

import com.revature.gms.dao.impl.SubjectsDaoImpl;
import com.revature.gms.model.Subjects;
import com.revature.gms.util.Logger;

public class SubjectsServices {
	SubjectsDaoImpl subjectsDaoImpl =new SubjectsDaoImpl();

	public boolean checkSubjectCode(int subjectCode) {
		return subjectsDaoImpl.checkSubjectCode(subjectCode);
	}

	public void viewSubjects() {
		List<Subjects> subjectsList=subjectsDaoImpl.viewSubjects();
		Logger.info("subject code:subject name");
		for(Subjects subjects:subjectsList) 
		{
			Logger.info(subjects.getId() +"\t:\t "+subjects.getName());
		}
	}

	public boolean checkSubjectName(String subjectName) {
		return subjectsDaoImpl.checkSubjectName(subjectName);
	}

}
