package com.aravinda.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.aravinda.taskmanager.service.ITaskManagerService;
import com.aravinda.taskmanager.dto.Task;

@CrossOrigin(origins = "*")
@RestController
public class TaskManagerController {
	
	@Autowired
	private ITaskManagerService taskManagerService;
	
	@RequestMapping(value="/addtask", method = RequestMethod.POST)
    public boolean addTask(@RequestBody Task task){
		
		System.out.println("entered addtask"+task.getParent().getTask());
		
    	System.out.println();
        return taskManagerService.save(task);
        
    }
	
	@RequestMapping(value="/viewtask", method = RequestMethod.GET)
    public List<Task> viewTask(){
    	List<Task> tasklist = taskManagerService.findAll();
    	for(Task task:tasklist) {
    		System.out.println("task start date>>>>>>>>>"+task.getStartDate());
    	}
    	return tasklist;
    }
	
	@RequestMapping(value="/updatetask", method = RequestMethod.POST)
    public boolean updateTask(@RequestBody Task task){
		
		long taskId = task.getTaskId();
    	
         Task tasktoupdate = taskManagerService.findByTaskId(taskId);
         tasktoupdate.setTask(task.getTask());
         tasktoupdate.setStartDate(task.getStartDate());
         tasktoupdate.setEndDate(task.getEndDate());
         tasktoupdate.setPriority(task.getPriority());
         tasktoupdate.setParent(task.getParent());
         return taskManagerService.save(tasktoupdate);
         
        
    }
	
	@RequestMapping(value="/endtask", method = RequestMethod.POST)
    public boolean endTask(@RequestBody Task task){
		
		long taskId = task.getTaskId();
    	
         Task tasktoupdate = taskManagerService.findByTaskId(taskId);
         tasktoupdate.setTask(task.getTask());
         tasktoupdate.setStartDate(task.getStartDate());
         long millis=System.currentTimeMillis();  
         java.sql.Date date=new java.sql.Date(millis);  
         tasktoupdate.setEndDate(date);
         tasktoupdate.setPriority(task.getPriority());
         tasktoupdate.setParent(task.getParent());
         tasktoupdate.setTaskended(true);
         return taskManagerService.save(tasktoupdate);
         
        
    }

}
