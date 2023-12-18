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

import com.gradingapplication.entity.Teacher;

public class TeacherBoundaryTest {

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
		Teacher teacher = new Teacher();
		teacher.setName("");
		Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameNotNull() throws IOException {
		Teacher teacher = new Teacher();
		teacher.setName(null);
		Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
