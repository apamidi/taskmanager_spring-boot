package com.aravinda.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.aravinda.taskmanager.service.ITaskManagerService;
import org.apache.log4j.Logger;
import com.aravinda.taskmanager.dto.Task;

@CrossOrigin(origins = "*")
@RestController
/*TaskManagerController provides rest services to the clients*/
public class TaskManagerController {
	
	final static Logger logger = Logger.getLogger(TaskManagerController.class.getName());
	
	@Autowired
	private ITaskManagerService taskManagerService;
	
	/* Add the task to the database*/
	@RequestMapping(value="/addtask", method = RequestMethod.POST)
    public boolean addTask(@RequestBody Task task){
		logger.info("Entered TaskManagerController - addTask()");
		logger.debug("task in TaskManagerController::addTask"+task);
		return taskManagerService.save(task);
        
    }
	
	/*Gets the list of tasks from the database*/
	@RequestMapping(value="/viewtask", method = RequestMethod.GET)
    public List<Task> viewTask(){
		logger.info("Entered TaskManagerController - viewTask()");
    	List<Task> tasklist = taskManagerService.findAll();
    	logger.debug("tasklist in TaskManagerController::viewTask"+tasklist);
    	return tasklist;
    }
	
	/*updates the task in the database*/
	@RequestMapping(value="/updatetask", method = RequestMethod.POST)
    public boolean updateTask(@RequestBody Task task){
		logger.info("Entered TaskManagerController - updateTask()");
		long taskId = task.getTaskId();
    	
         Task tasktoupdate = taskManagerService.findByTaskId(taskId);
         tasktoupdate.setTask(task.getTask());
         tasktoupdate.setStartDate(task.getStartDate());
         tasktoupdate.setEndDate(task.getEndDate());
         tasktoupdate.setPriority(task.getPriority());
         tasktoupdate.setParent(task.getParent());
         logger.debug("tasktoupdate in TaskManagerController::updateTask"+tasktoupdate);
         return taskManagerService.save(tasktoupdate);
         
        
    }
	
	/*Ends the task*/
	@RequestMapping(value="/endtask", method = RequestMethod.POST)
    public boolean endTask(@RequestBody Task task){
		logger.info("Entered TaskManagerController - endTask()");
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
         logger.debug("tasktoupdate in TaskManagerController::endTask"+tasktoupdate);
         return taskManagerService.save(tasktoupdate);
         
        
    }

}
