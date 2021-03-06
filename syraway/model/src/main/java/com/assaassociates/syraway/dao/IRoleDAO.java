/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;

import com.assaassociates.syraway.model.Role;

/**
 * @author waheb
 *
 */
public interface IRoleDAO {
	public Role getRoleById(Integer pRoleId);
	public void addRole(Role pRole);
	public void updateRole(Role pRole);
	public void removeRole(Role pRole);
	public void removeRole(Integer pRoleId);
	public List<Role> getAllRoles();
}
