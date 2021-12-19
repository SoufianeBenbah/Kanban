package org.rygn.kanban.domain;

import lombok.*;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Developer {

	private @Id @GeneratedValue Long id;
	  
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String password;
	
	private LocalDate startContract;


}
