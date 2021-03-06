package com.assaasoociates.syraway.controller;

import java.util.List;
import com.assaassociates.syraway.model.BusinessUnitPc;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Project;

public interface IProjectController {

	public void addProject(String pProjectId);
	
	public void addProject(Project pProject);
	
	public void updateProject(Project pProject);
	
	public Project getProject(String pProjectId); 
	
	public List<Project> getAllProjects();
	
	public List<Employee> getAllEmpls();
	
	public List<BusinessUnitPc> getAllBuPc();
}
