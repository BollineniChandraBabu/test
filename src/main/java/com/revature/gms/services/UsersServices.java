package com.revature.gms.services;

import java.util.Scanner;

import com.revature.gms.dao.UsersDao;
import com.revature.gms.dao.impl.UsersDaoImpl;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.loginPage.AdminLogin;
import com.revature.gms.loginPage.Login;
import com.revature.gms.loginPage.UserLogin;
import com.revature.gms.model.Users;
import com.revature.gms.validator.UsersValidator;

public class UsersServices {
	UsersValidator usersValidator=new UsersValidator();
	UsersDao userDAOImpl = new UsersDaoImpl();
	MarksServices marksServices=new MarksServices();
	Scanner scanner=new Scanner(System.in);
Users users=new Users();

	public Users login(String email, String password) throws ServiceException {
		users = null;
		try {
			users = userDAOImpl.login(email,password);
		} catch (DBException e) {
			throw new ServiceException("Unable to login");
			
		}
		return users;

	}

	public int insert(Users users) throws ServiceException {
		int id;
		try {
			
			id = userDAOImpl.insert(users);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to insert new users");	
		}
		return id;
	}
	public void validatingUser(Users users) throws ServiceException
	{
		if (users!= null) {
			if (users.isRoles()==true) {
				AdminLogin adminLogin=new AdminLogin();
				adminLogin.adminLogin();
			} else if (users.isRoles()==false) {
				UserLogin userLogin=new UserLogin();
				userLogin.userLogin(users);
			}
		} else {
			System.out.println("Invalid Login Credentials");
			Login login=new Login();
			login.login();
		}
	}

	public boolean validateName(String name) {
		boolean result=usersValidator.validateName(name);
		return result;
	}

	public String getEmail() {
		boolean result=false;
		String email;
		while(true) {
			System.out.println("enter your email");
			email=scanner.next();
			result=usersValidator.emailValidator(email);
			if(result==true) { 
			break;
			}
			else { System.out.println("please enter valid email id....");}
			}
		return email;
	}

	public boolean validateDate(String sDate) {
		boolean result=usersValidator.dateValidator(sDate);
		return result;
	}

	public boolean checkByMailId(String mailId) throws DBException
	{boolean result=false;
		try {
			result = userDAOImpl.checkByMailId(mailId);
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert new users");
			
		}
		return result;
	}
	
	
	public void activateAccount() throws ServiceException {
		boolean result =false;
		String password = null,confirmPassword = null;
		System.out.println("enter your id...");
		int id=marksServices.getNumber();
		String email="";
		System.out.println("please enter your email id...");
		while(result!=true) {
			email=scanner.next();
		result = usersValidator.emailValidator(email);
		if(result==false) { System.out.println("please enter valid email id....");}
		}
		result =false;
		
		
		
		while(result!=true) 
		{
		System.out.println("enter your new password...");
		password=scanner.next();
		System.out.println("re-enter your new password...");
		confirmPassword=scanner.next();
		if(!password.equals(confirmPassword))
		{
			System.out.println("password and confirm password should be same.....");
		}
		else {
		break;}
		}
		
		if(password.equals(confirmPassword)) 
		{
		boolean updateResult=userDAOImpl.activateAccount(email,id,password);
		if(updateResult==true) 
		{
			System.out.println("successfully activated....");
			Login login=new Login();
			login.login();
		}
		else 
		{
			System.out.println("check your details unable to activate....");
		}
		
		}
		

	}
	
}
