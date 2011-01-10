package com.assaassociates.syraway.exts.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Project;

public interface ITaskService extends Serializable{

	public void addTask();
	public void listTaskListener(AjaxBehaviorEvent event);
	public List<Project> getAllProject();
	public List<Employee> getAllEmployee();
	 
}
