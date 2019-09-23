package com.revature.gms.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UsersValidator {
	boolean result;
public boolean emailValidator(String email)
{
	result=false;
	String regex="^[A-Za-z0-9+_.-]+@+[a-z]+(.+)$";
	boolean result=email.matches(regex);
return result;
}

public boolean validateName(String name) {
	result=false;
	if(name.equals("") || name.equals(" ") || name==null || name.length()<4)
		{
			result= false;
		}
	else 
	{
		result=true;
	}
	return result;
}

public boolean dateValidator(String sDate) {
	result=false;
	try {
		new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
		result=true;
	} catch (ParseException e) {
		result=false;
	} 	
	return result;
}

}
