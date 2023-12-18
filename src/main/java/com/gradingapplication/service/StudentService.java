package com.gradingapplication.service;

import java.util.List;

import com.gradingapplication.dto.StudentDTO;

public interface StudentService {
	List<StudentDTO> getAllStudentsByTeacherId(Long teacherId);

	StudentDTO createStudentUnderTeacher(Long teacherId, StudentDTO studentDTO);

	StudentDTO updateStudentUnderTeacher(Long teacherId, Long studentId, StudentDTO studentDTO);

	boolean assignGradeToStudent(Long teacherId, Long studentId, String grade);
}
