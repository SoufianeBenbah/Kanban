package org.rygn.kanban.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private Integer nbHoursForecast;
	private Integer nbHoursReal;
	private LocalDate created;
	
	@ManyToOne
	private TaskType type;
	@ManyToOne
	private TaskStatus status;
	@ManyToMany(fetch=FetchType.EAGER)
    private Set<Developer> developers;
	
	public Task() {
		this.developers = new HashSet<>();
	}
	
	public void addDeveloper(Developer developer) {
		this.developers.add(developer);
	}
}
