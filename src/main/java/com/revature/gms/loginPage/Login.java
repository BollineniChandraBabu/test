package com.revature.gms.loginPage;
import java.util.Scanner;

import com.revature.gms.controller.UsersController;
import com.revature.gms.exception.ServiceException;

public class Login {
	
	public void login() throws ServiceException {
		String email=null;
		UsersController usersController = new UsersController();
		Scanner scanner =new Scanner(System.in);
		System.out.println("----------------------------\nplease login to use services");
		email=usersController.getEmail();
		boolean result=usersController.checkByMailId(email);
		if(result==false) 
		{
			System.out.println("email doesnot exist....\n contact Admin....");
			System.out.println("---------------------------------------------------------------");
			Index.starter();
		}
		System.out.println("enter your password :");
		String password=scanner.next();
		usersController.loginController(email, password);
		scanner.close();
		}
	
}
