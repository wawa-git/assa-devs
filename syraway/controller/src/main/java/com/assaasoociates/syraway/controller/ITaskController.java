package com.assaasoociates.syraway.controller;

import java.util.List;

import com.assaassociates.syraway.model.Task;

public interface ITaskController {

	public void addTask(Task oTask);
	public void updateTask(Task oTask);
	public Task getTask(Integer pTaskId); 
	public List<Task> getAllTasks();
	
}
