/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;
import com.assaassociates.syraway.model.Project;

/**
 * @author waheb
 *
 */
public interface IProjectDAO {
	
	public Project getProjectById(int pId);
	public List<Project> getProjectOfBuPc(String pBuPcId);
	public List<Project> getProjectOfManager(String pEmployeeId);
	public List<Project> getAllProjects();
	public void addProject(Project pProject);
	public void removeProject(Project pProject);
	public void removeProject(int pProjectPk);
	
}
