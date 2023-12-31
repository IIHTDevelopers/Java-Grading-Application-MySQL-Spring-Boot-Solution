package com.gradingapplication.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "grade")
	private String grade;

	@Column(name = "semester")
	private int semester;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	public Student() {
		super();
	}

	public Student(Long id, String name, String grade, int semester, Teacher teacher) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.semester = semester;
		this.teacher = teacher;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", grade=" + grade + ", semester=" + semester + ", teacher="
				+ teacher + "]";
	}
}
