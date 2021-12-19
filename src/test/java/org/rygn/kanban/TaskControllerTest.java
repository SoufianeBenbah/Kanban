package org.rygn.kanban;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rygn.kanban.domain.Task;
import org.rygn.kanban.service.TaskService;
import org.rygn.kanban.utils.Constants;
import org.rygn.kanban.utils.TaskMoveAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TaskControllerTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private TaskService taskService;

    @Test
    public void testGetTasks() throws Exception{
        mvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is("task1")))
                .andExpect(jsonPath("$[0].status.label", is("TODO")));
    }

    @Test
    public void testPostTask() throws Exception {
        Task newTask = new Task();
        newTask.setTitle("Task to test");
        newTask.setNbHoursForecast(0);
        newTask.setNbHoursReal(0);
        newTask.setType(this.taskService.findTaskType(Constants.TASK_TYPE_FEATURE_ID));

        ObjectMapper mapper = new ObjectMapper();
        byte[] taskAsBytes = mapper.writeValueAsBytes(newTask);

        mvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON).content(taskAsBytes))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Collection<Task> tasks = this.taskService.findAllTasks();

        boolean taskExists = false;

        for (Task task : tasks){
            if(task.getTitle().equals(newTask.getTitle())){
                Assert.assertEquals(Constants.TASK_STATUS_TODO_LABEL, task.getStatus().getLabel());
                taskExists = true;
            }
        }
        Assert.assertEquals(true,taskExists);


    }

    @Test
    public void testPatchTest() throws Exception {

        Task newTask = new Task();
        newTask.setTitle("Task to test");
        newTask.setNbHoursForecast(0);
        newTask.setNbHoursReal(0);
        newTask.setType(this.taskService.findTaskType(Constants.TASK_TYPE_FEATURE_ID));

        newTask = this.taskService.createTask(newTask);
        Collection<Task> tasks = this.taskService.findAllTasks();

        for(Task task : tasks){
            if (task.getTitle().equals(newTask.getTitle())) {
                TaskMoveAction moveRight = new TaskMoveAction();
                moveRight.setAction(Constants.MOVE_RIGHT_ACTION);

                ObjectMapper mapper = new ObjectMapper();
                byte[] moveRightAsBytes = mapper.writeValueAsBytes(moveRight);

                mvc.perform(patch("/tasks/"+ newTask.getId())
                            .contentType(MediaType.APPLICATION_JSON).content(moveRightAsBytes))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
            }

        }





    }


}
