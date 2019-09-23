package com.revature.gms.loginPage;
import com.revature.gms.controller.UsersController;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.services.MarksServices;

public class Index {
public static void main(String[] args) throws ServiceException {
	starter();
	}


	public static void starter() throws ServiceException {
	MarksServices marksServices=new MarksServices();
	System.out.println("welcome.......");
	System.out.println("select one service\n1.login\t2.activate account");
	int choice=marksServices.getNumber();
	switch(choice) 
	{
	case 1:
	{
		Login login= new Login();
		login.login(); break;
	}
	case 2:
	{
		UsersController usersController=new UsersController();	
		usersController.activateAccount();
		break;
	}
	default :
	{
		System.out.println("enter correct choice....");
		starter();
	}
	}
}
}