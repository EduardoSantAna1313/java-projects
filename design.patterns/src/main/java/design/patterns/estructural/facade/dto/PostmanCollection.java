package design.patterns.estructural.facade.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostmanCollection {

	private String id;

	private String name;

	private String owner;

	private Date createdAt;

}