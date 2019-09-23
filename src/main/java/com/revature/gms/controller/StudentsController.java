package com.revature.gms.controller;

import com.revature.gms.model.Students;
import com.revature.gms.services.StudentServices;

public class StudentsController {
StudentServices studentServices=new StudentServices();
	public Students checkStudentById(int studentId) {
		return studentServices.checkStudentById(studentId);
		
		
	}

}
