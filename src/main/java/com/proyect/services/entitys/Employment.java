package com.proyect.services.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employment")
public class Employment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String title;
	@Column(name = "key_skill")
    private String key_skill;
}
