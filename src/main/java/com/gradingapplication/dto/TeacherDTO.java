package com.gradingapplication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TeacherDTO {
	private Long id;

	@NotBlank
	@NotNull
	private String name;

	public TeacherDTO() {
		super();
	}

	public TeacherDTO(Long id, @NotBlank @NotNull String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TeacherDTO [id=" + id + ", name=" + name + "]";
	}
}
