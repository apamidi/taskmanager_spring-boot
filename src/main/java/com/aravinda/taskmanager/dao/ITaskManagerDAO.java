package com.aravinda.taskmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aravinda.taskmanager.dto.Task;

@Repository
public interface ITaskManagerDAO extends JpaRepository<Task, Long>{
	
	/*Save task details*/
	public Task save(Task task);
	
	/* Get the task details*/
	public List<Task> findAll();
	
	/*Get the task details for the given task Id*/
	public Task findByTaskId(long taskId);	
	
}
