/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.IUserRoleDAO;
import com.assaassociates.syraway.model.UserRole;

/**
 * @author waheb
 *
 */

@Component("userRoleController")
public class UserRoleControllerImpl implements IUserRoleController {

	@Autowired
	private IUserRoleDAO userRoleDAO;
	
	public IUserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	public void setUserRoleDAO(IUserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	public void addUserRole(UserRole pUserRole) {
		if(userRoleDAO != null){
			userRoleDAO.addUserRole(pUserRole);
		}
	}

	public void updateUserRole(UserRole pUserRole) {
		if(userRoleDAO != null){
			userRoleDAO.updateUserRole(pUserRole);
		}
	}

	public UserRole getUserRole(Integer pUserRoleId) {
		UserRole result = null;
		if(userRoleDAO != null){
			result = userRoleDAO.getUserRoleById(pUserRoleId);
		}
		return result;
	}

	public List<UserRole> getAllUserRoles() {
		List<UserRole> result = new ArrayList<UserRole>();
		if(userRoleDAO != null)
			result = userRoleDAO.getAllUserRoles();
		return result;
	}
}
