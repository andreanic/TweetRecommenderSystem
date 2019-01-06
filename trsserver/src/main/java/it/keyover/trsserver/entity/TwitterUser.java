package it.keyover.trsserver.entity;

import lombok.Data;

@Data
public class TwitterUser {
	private Long id;
	private String name;
	private String screenName;
}
