package com.aravinda.taskmanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aravinda.taskmanager.dao.ITaskManagerDAO;
import com.aravinda.taskmanager.dto.Task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TaskmanagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
/*TaskManagerController integration test*/
public class TaskManagerControllerIntegrationTest {
	
	@Autowired
    private MockMvc mvc;
 
    @Autowired
    private ITaskManagerDAO taskManagerDAO;
    
    @Value("${taskName}")
    private String taskName;
    
    /* tests if the task data exists in the database*/
    @Test
    public void givenTasks_whenGetTask_thenStatus200()
      throws Exception {
     
        createTestTask();
     
        mvc.perform(get("/viewtask")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content()
          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].task", is(taskName)));
    }
    
    /*creates the new task*/
    private void createTestTask() {
        Task task = new Task();
        task.setTask(taskName);
        taskManagerDAO.saveAndFlush(task);
    }

}
