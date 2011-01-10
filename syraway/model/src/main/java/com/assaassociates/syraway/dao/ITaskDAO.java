/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;
import com.assaassociates.syraway.model.Task;

/**
 * @author waheb
 *
 */
public interface ITaskDAO {
	public Task getTaskById(Integer pTaskId);
	public List<Task> getTaskOfProject(Integer pProjectId);
	public List<Task> getTaskOfEmployee(Integer pEmployeeId);
	public void addTask(Task pTask);
	public void updateTask(Task pTask);
	public void removeTask(Task pTask);
	public void removeTask(Integer pTaskId);
	public List<Task> getAllTasks();
}
