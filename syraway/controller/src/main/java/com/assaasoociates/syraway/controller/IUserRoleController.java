package com.assaasoociates.syraway.controller;

import java.util.List;

import com.assaassociates.syraway.model.UserRole;

public interface IUserRoleController {

	public void addUserRole(UserRole oUserRole);
	public void updateUserRole(UserRole oUserRole);
	public UserRole getUserRole(Integer pUserRoleId); 
	public List<UserRole> getAllUserRoles();
	
}
