package com.gradingapplication.exception;

import static com.gradingapplication.utils.MasterData.getStudentDTO;
import static com.gradingapplication.utils.TestUtils.currentTest;
import static com.gradingapplication.utils.TestUtils.exceptionTestFile;
import static com.gradingapplication.utils.TestUtils.testReport;
import static com.gradingapplication.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gradingapplication.controller.StudentController;
import com.gradingapplication.dto.StudentDTO;
import com.gradingapplication.service.StudentService;
import com.gradingapplication.utils.MasterData;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
public class StudentExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateStudentInvalidDataException() throws Exception {
		StudentDTO studentDTO = getStudentDTO();
		studentDTO.setName(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teachers/1/students")
				.content(MasterData.asJsonString(studentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateStudentInvalidDataException() throws Exception {
		StudentDTO studentDTO = getStudentDTO();
		studentDTO.setName(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/teachers/1/students/" + studentDTO.getId())
				.content(MasterData.asJsonString(studentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}
}
