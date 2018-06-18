package com.aravinda.taskmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aravinda.taskmanager.controller.TaskManagerController;
import com.aravinda.taskmanager.dto.Task;
import com.aravinda.taskmanager.service.ITaskManagerService;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskManagerController.class)
public class TaskManagerControllerTest {
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private ITaskManagerService taskManagerService;
    
    @Value("${task}")
    private String tasknme;
    
    /*Tests if the task list is being returned from database*/    
    @Test
    public void givenTasks_whenGetTasks_thenReturnJsonArray()
      throws Exception {
         
        Task task = new Task();
        task.setTask(tasknme);
     
        List<Task> allTasks = Arrays.asList(task);
     
        given(taskManagerService.findAll()).willReturn(allTasks);
     
        mvc.perform(get("/viewtask")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].task", is(task.getTask())));
    }
}
