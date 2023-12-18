package com.gradingapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gradingapplication.dto.StudentDTO;
import com.gradingapplication.service.StudentService;

@RestController
@RequestMapping("/teachers/{teacherId}/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudentsByTeacherId(@PathVariable Long teacherId) {
		List<StudentDTO> students = studentService.getAllStudentsByTeacherId(teacherId);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<StudentDTO> createStudentUnderTeacher(@PathVariable Long teacherId,
			@RequestBody @Valid StudentDTO studentDTO) {
		StudentDTO createdStudent = studentService.createStudentUnderTeacher(teacherId, studentDTO);
		return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	}

	@PutMapping("/{studentId}")
	public ResponseEntity<StudentDTO> updateStudentUnderTeacher(@PathVariable Long teacherId,
			@PathVariable Long studentId, @RequestBody @Valid StudentDTO studentDTO) {
		StudentDTO updatedStudent = studentService.updateStudentUnderTeacher(teacherId, studentId, studentDTO);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}

	@PatchMapping("/{studentId}/assignGrade")
	public ResponseEntity<Void> assignGradeToStudent(@PathVariable Long teacherId, @PathVariable Long studentId,
			@RequestParam String grade) {
		studentService.assignGradeToStudent(teacherId, studentId, grade);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
