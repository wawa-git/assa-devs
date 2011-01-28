/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.IRoleDAO;
import com.assaassociates.syraway.model.Role;

/**
 * @author waheb
 *
 */

@Component("roleController")
public class RoleControllerImpl implements IRoleController {

	@Autowired
	private IRoleDAO roleDAO;
	
	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void addRole(Role pRole) {
		if(roleDAO != null){
			roleDAO.addRole(pRole);
		}
	}

	public void updateRole(Role pRole) {
		if(roleDAO != null){
			roleDAO.updateRole(pRole);
		}
	}

	public Role getRole(Integer pRoleId) {
		Role result = null;
		if(roleDAO != null){
			result = roleDAO.getRoleById(pRoleId);
		}
		return result;
	}

	public List<Role> getAllRoles() {
		List<Role> result = new ArrayList<Role>();
		if(roleDAO != null)
			result = roleDAO.getAllRoles();
		return result;
	}
}
