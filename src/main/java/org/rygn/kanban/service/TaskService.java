package org.rygn.kanban.service;

import java.util.Collection;

import org.rygn.kanban.domain.ChangeLog;
import org.rygn.kanban.domain.Task;
import org.rygn.kanban.domain.TaskStatus;
import org.rygn.kanban.domain.TaskType;
import org.springframework.transaction.annotation.Transactional;

public interface TaskService {

	Collection<Task> findAllTasks();
	
	Task changeTaskStatus(Task task, TaskStatus targetStatus);
	
	Task findTask(Long id);
	
	TaskStatus findTaskStatus(Long id);

	TaskType findTaskType(Long id);

	Collection<ChangeLog> findChangeLogsForTask(Task task);
	
	boolean displayMoveRightForTask(Task task);
	
	boolean displayMoveLeftForTask(Task task);
	
	Task moveRightTask(Task task);
	
	Task moveLeftTask(Task task);

	Task createTask(Task task);

	void deleteTask(Long id);

	Collection<TaskType> findAllTaskTypes();
	
	Collection<TaskStatus> findAllTaskStatus();

}
