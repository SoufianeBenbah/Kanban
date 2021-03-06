package org.rygn.kanban.service;

import java.util.List;

import org.rygn.kanban.domain.Developer;

public interface DeveloperService {

	List<Developer> findAllDevelopers();

	Developer findDeveloper(Long id);
}
