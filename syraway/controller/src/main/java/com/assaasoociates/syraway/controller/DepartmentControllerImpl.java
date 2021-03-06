/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.IBusinessUnitExDAO;
import com.assaassociates.syraway.dao.IDepartmentDAO;
import com.assaassociates.syraway.model.BusinessUnit;
import com.assaassociates.syraway.model.BusinessUnitEx;
import com.assaassociates.syraway.model.Department;
import com.assaassociates.syraway.model.Employee;

/**
 * @author waheb
 *
 */

@Component("deptController")
public class DepartmentControllerImpl implements IDepartmentController {

	@Autowired
	private IDepartmentDAO deptDAO;
	
	@Autowired
	private IBusinessUnitExDAO businessUnitExDAO;

	public IDepartmentDAO getDeptDAO() {
		return deptDAO;
	}

	public void setDeptDAO(IDepartmentDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	public IBusinessUnitExDAO getBusinessUnitExDAO() {
		return businessUnitExDAO;
	}

	public void setBuExDAO(IBusinessUnitExDAO pBusinessUnitExDAO) {
		this.businessUnitExDAO = pBusinessUnitExDAO;
	}

	public void addDepartment(String pDeptId) {
		Department oDept = new Department();
		try {
			oDept.setDeptId(Integer.parseInt(pDeptId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
		}
		if(deptDAO != null){
			deptDAO.addDepartment(oDept);
		}
	}

	public void updateDepartment(Department pDept) {
		// TODO Auto-generated method stub
		
	}

	public Department getDepartment(String pDeptId) {
		Department result = null;
		if(deptDAO != null){
			try {
				result = deptDAO.getDepartmentById(Integer.valueOf(pDeptId));
			} catch (NumberFormatException e) {
			}
		}
		return result;
	}

	public List<Department> getAllDepts() {
		List<Department> result = new ArrayList<Department>();
		if(deptDAO != null)
			result = deptDAO.getAllDepts();
		return result;
	}

	public List<Employee> getAllEmpls() {
		// TODO Auto-generated method stub
		return new ArrayList<Employee>();
	}

	public List<BusinessUnit> getAllBu() {
		// TODO Auto-generated method stub
		return new ArrayList<BusinessUnit>();
	}
	
	public List<BusinessUnitEx> getAllBuEx() {
		if(businessUnitExDAO != null){
			return businessUnitExDAO.getAllBusinessUnitEx();
		}
		return new ArrayList<BusinessUnitEx>();
	}

	public void addDepartment(Department pDept) {
		if(deptDAO != null){
			deptDAO.addDepartment(pDept);
		}
		
	}
}
