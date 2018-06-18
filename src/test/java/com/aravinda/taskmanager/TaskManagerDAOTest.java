package com.aravinda.taskmanager;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.aravinda.taskmanager.dao.ITaskManagerDAO;
import com.aravinda.taskmanager.dto.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@SpringBootTest
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class TaskManagerDAOTest {
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ITaskManagerDAO taskManagerDAO;
    
    @Value("${taskName}")
    private String taskName;
    
    /*Tests if the task details is being returned for a particular task*/
    @Test
    public void whenFindById_thenReturnTask() {
        
        Task task = new Task();
        task.setTaskId((long)1);
        //entityManager.persist(task);
        //entityManager.flush();
     
        // when
        Task taskdetails = taskManagerDAO.findByTaskId(task.getTaskId());
    
        // then
        assertThat(taskdetails.getTask())
          .isEqualTo(taskName);
    }

}
