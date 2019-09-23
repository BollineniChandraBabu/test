package com.revature.gms.model;

import java.util.Date;

public class Users {
private int id;
private String name;
private String fatherName;
private String email;
private String password;
private boolean roles;
private Date dateOfJoining;
private Departments department;
private boolean active;
private boolean activeAccount;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getFatherName() {
	return fatherName;
}
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isRoles() {
	return roles;
}
public void setRoles(boolean roles) {
	this.roles = roles;
}
public Date getDateOfJoining() {
	return dateOfJoining;
}
public void setDateOfJoining(Date dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
public boolean isActiveAccount() {
	return activeAccount;
}
public void setActiveAccount(boolean activeAccount) {
	this.activeAccount = activeAccount;
}
public Departments getDepartment() {
	return department;
}
public void setDepartment(Departments department) {
	this.department = department;
}

}
