package com.gradingapplication.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gradingapplication.dto.StudentDTO;
import com.gradingapplication.dto.TeacherDTO;

public class MasterData {

	public static TeacherDTO getTeacherDTO() {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(1L);
		teacherDTO.setName("John Doe");
		return teacherDTO;
	}

	public static List<TeacherDTO> getTeacherDTOList() {
		List<TeacherDTO> teacherDTOList = new ArrayList<>();
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(1L);
		teacherDTO.setName("John Doe");
		teacherDTOList.add(teacherDTO);
		return teacherDTOList;
	}

	public static StudentDTO getStudentDTO() {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(1L);
		studentDTO.setName("Alice");
		studentDTO.setTeacherId(1L);
		studentDTO.setGrade("A");
		studentDTO.setSemester(1);
		return studentDTO;
	}

	public static List<StudentDTO> getStudentDTOList() {
		List<StudentDTO> studentDTOList = new ArrayList<>();
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(1L);
		studentDTO.setName("Alice");
		studentDTO.setTeacherId(1L);
		studentDTO.setGrade("A");
		studentDTO.setSemester(1);
		studentDTOList.add(studentDTO);
		return studentDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
