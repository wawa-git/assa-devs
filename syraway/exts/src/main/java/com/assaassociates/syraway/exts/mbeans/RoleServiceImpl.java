package com.assaassociates.syraway.exts.mbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaasoociates.syraway.controller.IRoleController;
import com.assaassociates.syraway.model.Role;

@ManagedBean(name="role")
@SessionScoped
public class RoleServiceImpl implements IRoleService {

private static final long serialVersionUID = 6726658729223350876L;
	
	private IRoleController oRoleController;
	private Role value;
	private List<Role> roleList;
	private String roleId;
	
	public RoleServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oRoleController = appContext.getBean("roleController", IRoleController.class);
		this.value = new Role();
		this.roleList = new ArrayList<Role>();
	}

	public IRoleController getRoleController() {
		return oRoleController;
	}

	public void setRoleController(IRoleController pRoleController) {
		this.oRoleController = pRoleController;
	}

	public Role getValue() {
		return value;
	}

	public void setValue(Role value) {
		this.value = value;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> pRoleList) {
		this.roleList = pRoleList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String pRoleId) {
		this.roleId = pRoleId;
		if(this.roleId != null){
			if(getRoleController() != null){
				try {
					value = getRoleController().getRole(Integer.valueOf(this.roleId));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
				}
			}
		}
	}

	@Override
	public void addRole(){
		if(this.oRoleController != null){
			this.oRoleController.addRole(value);
		}
	}

	@Override
	public void listRoleListener(AjaxBehaviorEvent event) {
		if(oRoleController != null){
			roleList = oRoleController.getAllRoles();
		}
	}
}
