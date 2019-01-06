package it.keyover.trsserver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class Hashtag {
	@Id
	private String id;
	private String value;
}
