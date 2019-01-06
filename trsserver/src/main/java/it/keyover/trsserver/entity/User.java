package it.keyover.trsserver.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {
	@Id
	private String id;
	private String username;
	private String password;
	private List<String> preferences;
}


