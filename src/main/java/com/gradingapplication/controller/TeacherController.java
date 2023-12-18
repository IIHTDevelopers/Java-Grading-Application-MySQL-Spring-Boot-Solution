package com.gradingapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradingapplication.dto.TeacherDTO;
import com.gradingapplication.service.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
		List<TeacherDTO> teachers = teacherService.getAllTeachers();
		return new ResponseEntity<>(teachers, HttpStatus.OK);
	}

	@GetMapping("/{teacherId}")
	public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long teacherId) {
		TeacherDTO teacher = teacherService.getTeacherById(teacherId);
		return new ResponseEntity<>(teacher, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TeacherDTO> createTeacher(@RequestBody  @Valid TeacherDTO teacherDTO) {
		TeacherDTO createdTeacher = teacherService.createTeacher(teacherDTO);
		return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
	}

	@PutMapping("/{teacherId}")
	public ResponseEntity<TeacherDTO> updateTeacherById(@PathVariable Long teacherId,
			@RequestBody @Valid TeacherDTO teacherDTO) {
		TeacherDTO updatedTeacher = teacherService.updateTeacherById(teacherId, teacherDTO);
		return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
	}

	@DeleteMapping("/{teacherId}")
	public ResponseEntity<Void> deleteTeacherById(@PathVariable Long teacherId) {
		teacherService.deleteTeacherById(teacherId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
