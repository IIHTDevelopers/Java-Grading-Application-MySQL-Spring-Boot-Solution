package com.gradingapplication.service;

import java.util.List;

import com.gradingapplication.dto.TeacherDTO;

public interface TeacherService {
	List<TeacherDTO> getAllTeachers();

	TeacherDTO getTeacherById(Long teacherId);

	TeacherDTO createTeacher(TeacherDTO teacherDTO);

	TeacherDTO updateTeacherById(Long teacherId, TeacherDTO teacherDTO);

	boolean deleteTeacherById(Long teacherId);
}
