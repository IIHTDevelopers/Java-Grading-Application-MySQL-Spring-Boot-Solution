package com.gradingapplication.boundary;

import static com.gradingapplication.utils.TestUtils.boundaryTestFile;
import static com.gradingapplication.utils.TestUtils.currentTest;
import static com.gradingapplication.utils.TestUtils.testReport;
import static com.gradingapplication.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gradingapplication.dto.StudentDTO;

public class StudentBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testNameNotBlank() throws IOException {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setName("");
		Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testGradeNotBlank() throws IOException {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setGrade("");
		Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testSemesterMinValue() throws IOException {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setSemester(-1); // Set a value less than allowed
		Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameNotNull() throws IOException {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setName(null);
		Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testGradeNotNull() throws IOException {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setGrade(null);
		Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testSemesterMaxValue() throws IOException {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setSemester(100); // Set a value greater than allowed
		Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
