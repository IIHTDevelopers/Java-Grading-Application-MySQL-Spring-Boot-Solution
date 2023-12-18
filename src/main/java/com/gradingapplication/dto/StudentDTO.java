package com.gradingapplication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StudentDTO {
	private Long id;

	@NotBlank
	@NotNull
	private String name;

	private Long teacherId;

	@NotBlank
	private String grade;

	private int semester;

	public StudentDTO() {
		super();
	}

	public StudentDTO(Long id, @NotBlank String name, Long teacherId, @NotBlank String grade, int semester) {
		super();
		this.id = id;
		this.name = name;
		this.teacherId = teacherId;
		this.grade = grade;
		this.semester = semester;
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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", name=" + name + ", teacherId=" + teacherId + ", grade=" + grade
				+ ", semester=" + semester + "]";
	}
}
