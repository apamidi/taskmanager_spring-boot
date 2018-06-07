package com.aravinda.taskmanager.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aravinda.taskmanager.dao.ITaskManagerDAO;
import com.aravinda.taskmanager.dto.Task;

@Service
public class TaskManagerServiceImpl implements ITaskManagerService {
	
	@Autowired
	private ITaskManagerDAO taskManagerDAO;
	
	
	
	public ITaskManagerDAO getTaskManagerDAO() {
		return taskManagerDAO;
	}

	public void setTaskManagerDAO(ITaskManagerDAO taskManagerDAO) {
		this.taskManagerDAO = taskManagerDAO;
	}

	/*Save task details*/
	@Override
	@Transactional(rollbackFor={Exception.class})
	public boolean save(Task task) {
		
		boolean saved = false;
		try {
			
			taskManagerDAO.save(task);
		saved = true;
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return saved;
	}
	
	/* Get the task details*/
	@Override
	public List<Task> findAll(){
		
		return taskManagerDAO.findAll();
		
	}
	
	/*Get the task details for the given task Id*/
	@Override
	public Task findByTaskId(Long taskId) {
		
		return taskManagerDAO.findByTaskId(taskId);
		
	}

}
