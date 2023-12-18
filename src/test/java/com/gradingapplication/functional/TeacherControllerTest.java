package com.gradingapplication.functional;

import static com.gradingapplication.utils.MasterData.getTeacherDTO;
import static com.gradingapplication.utils.MasterData.getTeacherDTOList;
import static com.gradingapplication.utils.TestUtils.asJsonString;
import static com.gradingapplication.utils.TestUtils.businessTestFile;
import static com.gradingapplication.utils.TestUtils.currentTest;
import static com.gradingapplication.utils.TestUtils.testReport;
import static com.gradingapplication.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gradingapplication.controller.TeacherController;
import com.gradingapplication.dto.TeacherDTO;
import com.gradingapplication.service.TeacherService;

@WebMvcTest(TeacherController.class)
@AutoConfigureMockMvc
public class TeacherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TeacherService teacherService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllTeachers() throws Exception {
		List<TeacherDTO> teacherDTOs = getTeacherDTOList();

		when(teacherService.getAllTeachers()).thenReturn(teacherDTOs);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teachers").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(asJsonString(teacherDTOs)),
				businessTestFile);
	}

	@Test
	public void testGetTeacherById() throws Exception {
		TeacherDTO teacherDTO = getTeacherDTO();

		when(teacherService.getTeacherById(teacherDTO.getId())).thenReturn(teacherDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teachers/" + teacherDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(asJsonString(teacherDTO)),
				businessTestFile);
	}

	@Test
	public void testCreateTeacher() throws Exception {
		TeacherDTO teacherDTO = getTeacherDTO();

		when(teacherService.createTeacher(any())).thenReturn(teacherDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teachers").content(asJsonString(teacherDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(asJsonString(teacherDTO)),
				businessTestFile);
	}

	@Test
	public void testUpdateTeacherById() throws Exception {
		TeacherDTO teacherDTO = getTeacherDTO();

		when(teacherService.updateTeacherById(eq(teacherDTO.getId()), any())).thenReturn(teacherDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/teachers/" + teacherDTO.getId())
				.content(asJsonString(teacherDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(asJsonString(teacherDTO)),
				businessTestFile);
	}

	@Test
	public void testDeleteTeacherById() throws Exception {
		long teacherId = 1L;

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/teachers/" + teacherId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 204, businessTestFile);
	}
}
