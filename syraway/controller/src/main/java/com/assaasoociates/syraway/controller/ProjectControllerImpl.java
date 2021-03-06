/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.IBusinessUnitPcDAO;
import com.assaassociates.syraway.dao.IEmployeeDAO;
import com.assaassociates.syraway.dao.IProjectDAO;
import com.assaassociates.syraway.model.BusinessUnitPc;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Project;

/**
 * @author waheb
 *
 */

@Component("projectController")
public class ProjectControllerImpl implements IProjectController {

	@Autowired
	private IProjectDAO projectDAO;
	
	@Autowired
	private IEmployeeDAO employeeDAO;
	
	@Autowired
	private IBusinessUnitPcDAO businessUnitPcDAO;
	
	public IBusinessUnitPcDAO getBusinessUnitPcDAO() {
		return businessUnitPcDAO;
	}

	public void setBusinessUnitPcDAO(IBusinessUnitPcDAO pBusinessUnitPcDAO) {
		this.businessUnitPcDAO = pBusinessUnitPcDAO;
	}

	public IProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(IProjectDAO pProjectDAO) {
		this.projectDAO = pProjectDAO;
	}

	public IEmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(IEmployeeDAO pEmployeeDAO) {
		this.employeeDAO = pEmployeeDAO;
	}


	public void addProject(String pProjectId) {
		Project oProject = new Project();
		try {
			oProject.setProjectId(Integer.valueOf(pProjectId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
		}
		if(projectDAO != null){
			projectDAO.addProject(oProject);
		}
	}
	
	public void addProject(Project pProject) {
		if(projectDAO != null){
			projectDAO.addProject(pProject);
		}
	}

	public void updateProject(Project pProject) {
		// TODO Auto-generated method stub
	}

	public Project getProject(String pProjectId) {
		Project result = null;
		if(projectDAO != null){
			try {
				result = projectDAO.getProjectById(Integer.valueOf(pProjectId));
			} catch (NumberFormatException e) {
				
			}
		}
		return result;
	}

	public List<Project> getAllProjects() {
		List<Project> result = new ArrayList<Project>();
		if(projectDAO != null)
			result = projectDAO.getAllProjects();
		return result;
	}

	public List<Employee> getAllEmpls() {
		List<Employee> result = new ArrayList<Employee>();
		if(employeeDAO != null)
			result = employeeDAO.getAllEmployee();
		return result;
	}

	public List<BusinessUnitPc> getAllBuPc() {
		List<BusinessUnitPc> result = new ArrayList<BusinessUnitPc>();
		if(businessUnitPcDAO != null)
			result = businessUnitPcDAO.getAllBusinessUnitPc();
		return result;
	}
}
