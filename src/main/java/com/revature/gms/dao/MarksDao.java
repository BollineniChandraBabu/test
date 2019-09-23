package com.revature.gms.dao;

import java.util.List;

import com.revature.gms.model.Marks;

public interface MarksDao {
	public List<Marks> findMaxMarks();
	public List<Marks> getMarksById(int id);
	public boolean insertMarks(Marks marks) ;
	public boolean updateMarks(Marks marks);
	public int getAverage(int id) ;
	public int getMaxAverage();
	public List<Marks> viewBySubjectCode(int subjectCode);
	public List<Marks> viewBySubjectName(String subjectName);
	public Marks checkAvailability(int studentId,int subjectId);
	public boolean checkSubjectById(int subjectId);
	
}
