package com.assaasoociates.syraway.controller;

import java.util.List;

import com.assaassociates.syraway.model.Role;

public interface IRoleController {

	public void addRole(Role oRole);
	public void updateRole(Role oRole);
	public Role getRole(Integer pRoleId); 
	public List<Role> getAllRoles();
	
}
