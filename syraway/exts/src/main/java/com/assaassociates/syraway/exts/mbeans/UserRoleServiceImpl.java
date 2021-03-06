package com.assaassociates.syraway.exts.mbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.assaasoociates.syraway.controller.IBusinessUnitController;
import com.assaasoociates.syraway.controller.IEmployeeController;
import com.assaasoociates.syraway.controller.IRoleController;
import com.assaasoociates.syraway.controller.IUserRoleController;
import com.assaassociates.syraway.model.BusinessUnit;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Role;
import com.assaassociates.syraway.model.UserRole;

@ManagedBean(name="userRole")
@SessionScoped
public class UserRoleServiceImpl implements IUserRoleService {

private static final long serialVersionUID = 6726658729223350876L;
	
	private IUserRoleController oUserRoleController;
	private IRoleController oRoleController;
	private IBusinessUnitController oBusinessUnitController;
	private IEmployeeController oEmployeeController;
	private UserRole value;
	private List<UserRole> userRoleList;
	private String roleId;
	private String emplId;
	private String userRoleId;
	private String buId;
	
	public IUserRoleController getUserRoleController() {
		return oUserRoleController;
	}

	public void setUserRoleController(IUserRoleController pUserRoleController) {
		this.oUserRoleController = pUserRoleController;
	}

	public IRoleController getRoleController() {
		return oRoleController;
	}

	public void setRoleController(IRoleController pRoleController) {
		this.oRoleController = pRoleController;
	}

	public IBusinessUnitController getBusinessUnitController() {
		return oBusinessUnitController;
	}

	public void setBusinessUnitController(
			IBusinessUnitController pBusinessUnitController) {
		this.oBusinessUnitController = pBusinessUnitController;
	}

	public UserRole getValue() {
		return value;
	}

	public void setValue(UserRole value) {
		this.value = value;
	}

	public IEmployeeController getEmployeeController() {
		return oEmployeeController;
	}

	public void setEmployeeController(IEmployeeController pEmployeeController) {
		this.oEmployeeController = pEmployeeController;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> pUserRoleList) {
		this.userRoleList = pUserRoleList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getBuId() {
		return buId;
	}

	public void setBuId(String buId) {
		this.buId = buId;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getUserRoleId() {
		return userRoleId;
	}
	
	public void setUserRoleId(String pUserRoleId) {
		this.userRoleId = pUserRoleId;
		if(this.userRoleId != null){
			if(getUserRoleController() != null){
				try {
					value = getUserRoleController().getUserRole(Integer.valueOf(this.userRoleId));
					if(value != null){
						setRoleId(String.valueOf(value.getSwRole().getRoleId()));
						setEmplId(String.valueOf(value.getSwEmployee().getEmplId()));
						setBuId(String.valueOf(value.getSwBus().getBuId()));
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public UserRoleServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oUserRoleController = appContext.getBean("userRoleController", IUserRoleController.class);
		this.oRoleController = appContext.getBean("roleController", IRoleController.class);
		this.oEmployeeController = appContext.getBean("emplController", IEmployeeController.class);
		this.oBusinessUnitController = appContext.getBean("buController",IBusinessUnitController.class);
		this.value = new UserRole();
		this.userRoleList = new ArrayList<UserRole>();
	}
	
	@Override
	public void addUserRole(){
		if(this.oUserRoleController != null){
			if(getEmplId() != null && !"".equals(getEmplId().trim())){
				value.setSwEmployee(getEmployeeController().getEmployee(getEmplId()));
			}
			if(getRoleId() != null && !"".equals(getRoleId().trim())){
				value.setSwRole(getRoleController().getRole(Integer.valueOf(getRoleId())));
			}
			if(getBuId() != null && !"".equals(getBuId().trim())){
				value.setSwBus(getBusinessUnitController().getBusinessUnit(getRoleId()));
			}
			this.oUserRoleController.addUserRole(value);
		}
	}

	@Override
	public void listUserRoleListener(AjaxBehaviorEvent event) {
		if(oUserRoleController != null){
			userRoleList = oUserRoleController.getAllUserRoles();
		}
	}

	@Override
	public List<Role> getAllRole() {
		if(oRoleController != null){
			return oRoleController.getAllRoles();
		}
		return new ArrayList<Role>();
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		if(oEmployeeController != null){
			return oEmployeeController.getAllEmpls();
		}
		return new ArrayList<Employee>();
	}
	
	@Override
	public List<BusinessUnit> getAllBu() {
		if(oBusinessUnitController != null){
			return oBusinessUnitController.getAllBu();
		}
		return new ArrayList<BusinessUnit>();
	}
}
