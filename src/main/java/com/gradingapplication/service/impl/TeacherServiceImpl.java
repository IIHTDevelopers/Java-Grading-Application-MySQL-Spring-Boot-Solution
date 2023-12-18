package com.gradingapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingapplication.dto.TeacherDTO;
import com.gradingapplication.entity.Teacher;
import com.gradingapplication.exception.ResourceNotFoundException;
import com.gradingapplication.repo.TeacherRepository;
import com.gradingapplication.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public List<TeacherDTO> getAllTeachers() {
		List<Teacher> teachers = teacherRepository.findAll();
		return teachers.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public TeacherDTO getTeacherById(Long teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));
		return convertToDTO(teacher);
	}

	@Override
	public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
		Teacher newTeacher = new Teacher();
		newTeacher.setName(teacherDTO.getName());
		Teacher savedTeacher = teacherRepository.save(newTeacher);
		return convertToDTO(savedTeacher);
	}

	@Override
	public TeacherDTO updateTeacherById(Long teacherId, TeacherDTO teacherDTO) {
		Teacher existingTeacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));
		existingTeacher.setName(teacherDTO.getName());
		Teacher updatedTeacher = teacherRepository.save(existingTeacher);
		return convertToDTO(updatedTeacher);
	}

	@Override
	public boolean deleteTeacherById(Long teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));
		teacherRepository.delete(teacher);
		return true;
	}

	private TeacherDTO convertToDTO(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(teacher.getId());
		teacherDTO.setName(teacher.getName());
		return teacherDTO;
	}
}
