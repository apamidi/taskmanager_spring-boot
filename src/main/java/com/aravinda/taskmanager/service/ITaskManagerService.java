package com.aravinda.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aravinda.taskmanager.dto.Task;


public interface ITaskManagerService {

	/*Save task details*/
	public boolean save(Task task);
	
	/* Get the task details*/
	public List<Task> findAll();
	
	/*Get the task details for the given task Id*/
	public Task findByTaskId(Long taskId);	
	
}
