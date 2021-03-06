/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.ITaskDAO;
import com.assaassociates.syraway.model.Task;

/**
 * @author waheb
 *
 */

@Component("taskController")
public class TaskControllerImpl implements ITaskController {

	@Autowired
	private ITaskDAO taskDAO;
	
	public ITaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(ITaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public void addTask(Task pTask) {
		if(taskDAO != null){
			taskDAO.addTask(pTask);
		}
	}

	public void updateTask(Task pTask) {
		if(taskDAO != null){
			taskDAO.updateTask(pTask);
		}
	}

	public Task getTask(Integer pTaskId) {
		Task result = null;
		if(taskDAO != null){
			result = taskDAO.getTaskById(pTaskId);
		}
		return result;
	}

	public List<Task> getAllTasks() {
		List<Task> result = new ArrayList<Task>();
		if(taskDAO != null)
			result = taskDAO.getAllTasks();
		return result;
	}
}
