package com.gradingapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingapplication.dto.StudentDTO;
import com.gradingapplication.entity.Student;
import com.gradingapplication.entity.Teacher;
import com.gradingapplication.exception.ResourceNotFoundException;
import com.gradingapplication.repo.StudentRepository;
import com.gradingapplication.repo.TeacherRepository;
import com.gradingapplication.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public List<StudentDTO> getAllStudentsByTeacherId(Long teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

		List<Student> students = teacher.getStudents();
		return students.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public StudentDTO createStudentUnderTeacher(Long teacherId, StudentDTO studentDTO) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

		Student newStudent = new Student();
		newStudent.setName(studentDTO.getName());
		newStudent.setGrade(studentDTO.getGrade());
		newStudent.setSemester(studentDTO.getSemester());
		newStudent.setTeacher(teacher);

		Student savedStudent = studentRepository.save(newStudent);
		return convertToDTO(savedStudent);
	}

	@Override
	public StudentDTO updateStudentUnderTeacher(Long teacherId, Long studentId, StudentDTO studentDTO) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

		Student existingStudent = teacher.getStudents().stream().filter(student -> student.getId().equals(studentId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

		existingStudent.setName(studentDTO.getName());
		existingStudent.setGrade(studentDTO.getGrade());
		existingStudent.setSemester(studentDTO.getSemester());

		Student updatedStudent = studentRepository.save(existingStudent);
		return convertToDTO(updatedStudent);
	}

	@Override
	public boolean assignGradeToStudent(Long teacherId, Long studentId, String grade) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

		Student student = teacher.getStudents().stream().filter(s -> s.getId().equals(studentId)).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

		student.setGrade(grade);
		studentRepository.save(student);
		return true;
	}

	private StudentDTO convertToDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setGrade(student.getGrade());
		studentDTO.setSemester(student.getSemester());
		// Map other properties if any

		return studentDTO;
	}
}
