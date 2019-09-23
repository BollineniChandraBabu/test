package com.revature.gms.loginPage;
import java.util.Scanner;

import com.revature.gms.controller.UsersController;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.util.Logger;

public class Login {
	
	public void login() throws ServiceException {
		String email=null;
		UsersController usersController = new UsersController();
		Scanner scanner =new Scanner(System.in);
		Logger.info("----------------------------\nplease login to use services");
		email=usersController.getEmail();
		boolean result=usersController.checkByMailId(email);
		if(result==false) 
		{
			Logger.error("email doesnot exist....\n contact Admin....");
			Logger.info("---------------------------------------------------------------");
			Index.starter();
		}
		Logger.info("enter your password :");
		String password=scanner.next();
		usersController.loginController(email, password);
		scanner.close();
		}
	
}
