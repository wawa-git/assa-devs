/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;

import com.assaassociates.syraway.model.UserRole;

/**
 * @author waheb
 *
 */
public interface IUserRoleDAO {
	public UserRole getUserRoleById(Integer pUserRoleId);
	public List<UserRole> getUserRoleOfEmployee(Integer pEmployeeId);
	public void addUserRole(UserRole pUserRole);
	public void updateUserRole(UserRole pUserRole);
	public void removeUserRole(UserRole pUserRole);
	public void removeUserRole(Integer pUserRoleId);
	public List<UserRole> getAllUserRoles();
}
