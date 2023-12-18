package com.gradingapplication.functional;

import static com.gradingapplication.utils.MasterData.getStudentDTO;
import static com.gradingapplication.utils.MasterData.getStudentDTOList;
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

import com.gradingapplication.controller.StudentController;
import com.gradingapplication.dto.StudentDTO;
import com.gradingapplication.service.StudentService;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllStudentsByTeacherId() throws Exception {
		long teacherId = 1L;
		List<StudentDTO> studentDTOs = getStudentDTOList();

		when(studentService.getAllStudentsByTeacherId(teacherId)).thenReturn(studentDTOs);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teachers/" + teacherId + "/students")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(asJsonString(studentDTOs)),
				businessTestFile);
	}

	@Test
	public void testCreateStudentUnderTeacher() throws Exception {
		long teacherId = 1L;
		StudentDTO studentDTO = getStudentDTO();

		when(studentService.createStudentUnderTeacher(eq(teacherId), any())).thenReturn(studentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/teachers/" + teacherId + "/students")
				.content(asJsonString(studentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(asJsonString(studentDTO)),
				businessTestFile);
	}

	@Test
	public void testUpdateStudentUnderTeacher() throws Exception {
		long teacherId = 1L;
		long studentId = 1L;
		StudentDTO studentDTO = getStudentDTO();

		when(studentService.updateStudentUnderTeacher(eq(teacherId), eq(studentId), any())).thenReturn(studentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/teachers/" + teacherId + "/students/" + studentId)
				.content(asJsonString(studentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(asJsonString(studentDTO)),
				businessTestFile);
	}

	@Test
	public void testAssignGradeToStudent() throws Exception {
		long teacherId = 1L;
		long studentId = 1L;
		String grade = "A";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.patch("/teachers/" + teacherId + "/students/" + studentId + "/assignGrade").param("grade", grade)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200, businessTestFile);
	}
}
