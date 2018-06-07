package com.aravinda.taskmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.aravinda.taskmanager.dao.ITaskManagerDAO;
import com.aravinda.taskmanager.dto.Task;
import com.aravinda.taskmanager.service.ITaskManagerService;
import com.aravinda.taskmanager.service.TaskManagerServiceImpl;

@RunWith(SpringRunner.class)
public class TaskManagerServiceImplTest {
	
	@TestConfiguration
    static class TaskManagerServiceImplTestContextConfiguration {
  
        @Bean
        public TaskManagerServiceImpl taskManagerService() {
            return new TaskManagerServiceImpl();
        }
    }
 
    @Autowired
    private TaskManagerServiceImpl taskManagerService;
 
    @MockBean
    private ITaskManagerDAO taskManagerDAO;
    
    @Before
    public void setUp() {
        Task task = new Task();
        task.setTaskId((long)1);
        task.setTask("integration testing");
        Mockito.when(taskManagerDAO.findByTaskId(task.getTaskId()))
          .thenReturn(task);
        taskManagerService.setTaskManagerDAO(taskManagerDAO);
        
    }
    
    @Test
    public void whenValidName_thenTaskShouldBeFound() {
       
        Task taskdetails = taskManagerService.findByTaskId((long)1);
       
         assertThat(taskdetails.getTask())
          .isEqualTo("integration testing");
     }

}
