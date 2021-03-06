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
import com.assaassociates.syraway.dao.IEmployeeDAO;
import com.assaassociates.syraway.model.BusinessUnitEx;
import com.assaassociates.syraway.model.Department;
import com.assaassociates.syraway.model.Employee;

/**
 * @author waheb
 *
 */

@Component("emplController")
public class EmployeeControllerImpl implements IEmployeeController {

	@Autowired
	private IEmployeeDAO emplDAO;
	
	@Autowired
	private IBusinessUnitExDAO businessUnitExDAO;
	
	@Autowired
	private IDepartmentDAO deptDAO;

	public IEmployeeDAO getEmplDAO() {
		return emplDAO;
	}

	public void setEmplDAO(IEmployeeDAO emplDAO) {
		this.emplDAO = emplDAO;
	}

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

	public void addEmployee(String pEmplId) {
		Employee oEmpl = new Employee();
		try {
			oEmpl.setEmplId(Integer.valueOf(pEmplId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
		}

		if(emplDAO != null){
			emplDAO.addEmployee(oEmpl);
		}
	}
	
	public void addEmployee(Employee pEmpl) {
		if(emplDAO != null){
			emplDAO.addEmployee(pEmpl);
		}
	}

	public void updateEmployee(Employee pEmpl) {
		// TODO Auto-generated method stub
		
	}

	public Employee getEmployee(String pEmplId) {
		Employee result = null;
		if(emplDAO != null){
			result = emplDAO.getEmployeeById(pEmplId);
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
		if(emplDAO != null){
			return emplDAO.getAllEmployee();
		}
		return new ArrayList<Employee>();
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
