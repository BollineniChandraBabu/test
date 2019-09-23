package com.revature.gms.dao;

import java.util.List;

import com.revature.gms.model.Grades;

public interface GradesDao {
	public boolean updateGrade(Grades grades);
	public List<Grades> viewGrades();
	public String getGrade(int mark);
	public boolean checkGradeAvailability(char grade);
}
