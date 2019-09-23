package com.revature.gms.dao;

import java.util.List;

import com.revature.gms.model.Subjects;

public interface SubjectsDao {
	public boolean checkSubjectCode(int id);
	public List<Subjects> viewSubjects() ;
	public boolean checkSubjectName(String subjectName);
}
