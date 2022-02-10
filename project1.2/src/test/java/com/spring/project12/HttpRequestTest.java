package com.spring.project12;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project12.controllers.DepartmentController;
import com.spring.project12.models.Department;
import com.spring.project12.services.DepartmentService;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class HttpRequestTest {
//
//	@LocalServerPort
//	private int port;
//
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@Test
//	public void defaultShouldReturnWelcomeMsg() throws Exception {
//		ResponseEntity<String> response = restTemplate
//				.getForEntity(new URL("http://localhost:" + port + "/").toString(), String.class);
//		assertEquals("Welcome to Employee/Department Management System", response.getBody());
//	}
//	
//	@Test
//	public void getAllDepartments() throws Exception {
//		ResponseEntity<String> response = restTemplate
//				.getForEntity(new URL("http://localhost:" + port + "/department").toString(), String.class);
//		assertEquals("[{\"id\":1,\"name\":\"Tech-Mid\",\"description\":\"This is Tech-Mid department\"},{\"id\":2,\"name\":\"Tech-UPI\",\"description\":\"This is UPI department\"},{\"id\":4,\"name\":\"Tech-App\",\"description\":\"This is Tech-App department\"}]", response.getBody());
//	}
//}

//@SpringBootTest
//@AutoConfigureMockMvc
//public class HttpRequestTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	public void defaultShouldReturnWelcomeMsg() throws Exception {
//		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Welcome to Employee/Department Management System")));
//	}
//
//	@Test
//	public void getAllDepartments() throws Exception {
//		this.mockMvc.perform(get("/department")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(
//				"[{'id':1,'name':'Tech-Mid','description':'This is Tech-Mid department'},{'id':2,'name':'Tech-UPI','description':'This is UPI department'},{'id':4,'name':'Tech-App','description':'This is Tech-App department'},{'id':3,'name':'Tech-Middle','description':'This is Tech-Middle department'}]"));
//	}
//
//	@Test
//	public void postDepartmentShouldReturnAddedDepartment() throws Exception {
//
//		String json = "{ " + "\"id\": 3, " + "\"name\": \"Tech-Middle\", "
//				+ "\"description\": \"This is Tech-Middle department\" " + "}";
//		this.mockMvc.perform(post("/department/{id}", 3).contentType(MediaType.APPLICATION_JSON).content(json))
//				.andDo(print()).andExpect(status().isOk()).andExpect(content()
//						.json("{ 'id': 3, 'name': 'Tech-Middle', 'description': 'This is Tech-Middle department' }"));
//	}
//}

@WebMvcTest(DepartmentController.class)
public class HttpRequestTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void defaultShouldReturnWelcomeMsg() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")).andReturn();
		assertEquals(mvcResult.getResponse().getContentAsString(), "{\"response\":\"Welcome to Employee/Department Management System\"}");
	}

	@Test
	public void getAllDepartments() throws Exception {
		List<Department> departments = new ArrayList<>();
		Department department1 = new Department(1, "Tech-Mid", "This is Tech-Mid department",1000.0);
		Department department2 = new Department(2, "Tech-UPI", "This is Tech-UPI department",2000.0);

		departments.add(department1);
		departments.add(department2);

		Mockito.when(departmentService.getAllDepartments()).thenReturn(departments);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/department").contentType(MediaType.APPLICATION_JSON))
				.andReturn();		
		assertEquals(result.getResponse().getContentAsString(), "[{\"id\":1,\"name\":\"Tech-Mid\",\"description\":\"This is Tech-Mid department\",\"fund\":1000.0},{\"id\":2,\"name\":\"Tech-UPI\",\"description\":\"This is Tech-UPI department\",\"fund\":2000.0}]");
	}

	@Test
	public void postDepartmentShouldReturnAddedDepartment() throws Exception {

		Department department = new Department(5, "Tech-5", "This is Tech-5 department",5000.0);

		Mockito.when(departmentService.addDepartment(department)).thenReturn(department);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/department/{id}", 5).contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsBytes(department))).andReturn();
		//System.out.println(mvcResult.getResponse().getContentAsString());
		assertEquals(mvcResult.getResponse().getContentAsString(), "{\"id\":5,\"name\":\"Tech-5\",\"description\":\"This is Tech-5 department\",\"fund\":5000.0}");
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/department/{id}",5)
//				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
//				.characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(department));
//
//		mockMvc.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(5)))
//		.andExpect(jsonPath("$.name", is("Tech-5"))).andExpect(jsonPath("$.description", is("This is Tech-5 department")))
//				.andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(department)));
	}
}