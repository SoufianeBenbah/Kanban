package org.rygn.kanban.controller;

import org.rygn.kanban.domain.Task;
import org.rygn.kanban.domain.TaskStatus;
import org.rygn.kanban.domain.TaskType;
import org.rygn.kanban.service.TaskService;
import org.rygn.kanban.utils.Constants;
import org.rygn.kanban.utils.TaskMoveAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class TaskController {


    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    Collection<Task> findAllTasks() {
        return this.taskService.findAllTasks();
    }

    @GetMapping("/tasks/{id}")
    Task findTask(@PathVariable long id) {
        return this.taskService.findTask(id);
    }

    @GetMapping("/task_status")
    Collection<TaskStatus> findAllTaskStatus() {
        return this.taskService.findAllTaskStatus();
    }

    @GetMapping("/task_type")
    Collection<TaskType> findAllTaskType() {
        return this.taskService.findAllTaskTypes();
    }

    @PostMapping("/tasks")
    Task createTask(@RequestBody Task newTask) {
        return this.taskService.createTask(newTask);
    }

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable Long id) {
        this.taskService.deleteTask(id);
    }

    @PatchMapping("/tasks/{id}")
    Task moveTask(@RequestBody TaskMoveAction taskMoveAction, @PathVariable Long id){
        Task task = this.taskService.findTask(id);

        if (Constants.MOVE_LEFT_ACTION.equals(taskMoveAction.getAction())) {
            task = this.taskService.moveLeftTask(task);
        }
        else if (Constants.MOVE_RIGHT_ACTION.equals(taskMoveAction.getAction())) {
            task = this.taskService.moveRightTask(task);
        }
        return task;
    }
}
