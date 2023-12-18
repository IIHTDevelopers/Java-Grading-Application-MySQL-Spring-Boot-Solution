package com.gradingapplication.exception;

import static com.gradingapplication.utils.MasterData.getTeacherDTO;
import static com.gradingapplication.utils.TestUtils.currentTest;
import static com.gradingapplication.utils.TestUtils.exceptionTestFile;
import static com.gradingapplication.utils.TestUtils.testReport;
import static com.gradingapplication.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

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

import com.gradingapplication.controller.TeacherController;
import com.gradingapplication.dto.TeacherDTO;
import com.gradingapplication.service.TeacherService;
import com.gradingapplication.utils.MasterData;

@WebMvcTest(TeacherController.class)
@AutoConfigureMockMvc
public class TeacherExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TeacherService teacherService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateTeacherInvalidDataException() throws Exception {
		TeacherDTO teacherDTO = getTeacherDTO();
		teacherDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teachers")
				.content(MasterData.asJsonString(teacherDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateTeacherInvalidDataException() throws Exception {
		TeacherDTO teacherDTO = getTeacherDTO();
		teacherDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/teachers/" + teacherDTO.getId())
				.content(MasterData.asJsonString(teacherDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetTeacherByIdResourceNotFoundException() throws Exception {
		TeacherDTO teacherDTO = getTeacherDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Teacher not found");

		when(this.teacherService.getTeacherById(teacherDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Teacher not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teachers/" + teacherDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
