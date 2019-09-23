package com.revature.gms.dao;

import com.revature.gms.model.Users;

public interface UsersDao {

Users login(String email, String password);
int insert(Users users);
boolean checkByMailId(String mailId);
boolean activateAccount(String email, int id, String password);
}
