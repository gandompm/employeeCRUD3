package com.root.springboot.demo.controller;


import com.root.springboot.demo.dao.EmployeeRepository;
import com.root.springboot.demo.entity.Employee;
import com.root.springboot.demo.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @Mock
    EmployeeService employeeServiceMock;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeAll
    public static void setup() {
        request = new MockHttpServletRequest();
        request.setParameter("firstname", "Parham");
        request.setParameter("lastname", "Gkardf");
        request.setParameter("email", "jadffk@gmail.com");
    }

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute("insert into employee(id, first_name, last_name, email) " +
                "values (3, 'Parham', 'Gkar', 'parham@gmail.com')");
    }

    @Test
    @WithMockUser(username = "Parham", roles = {"Admin"})
    public void getEmployeesListWithAuthenticatedUser() throws Exception {

        // mocking employee service findAll function
        Employee employeeOne = new Employee("Parham", "Gkar", "parham@gmail.com");
        Employee employeeTwo = new Employee("Pouria", "Gkar", "pouria@gmail.com");

        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employeeOne, employeeTwo));

        when(employeeServiceMock.findAll()).thenReturn(employeeList);

        assertIterableEquals(employeeList, employeeServiceMock.findAll());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employees/list"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "employees/list-employees");
    }

    @Test
    @WithAnonymousUser
    public void getEmployeeListWithAnonymousUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/list"))
                .andExpect(redirectedUrl("http://localhost/showMyLoginPage")).andReturn();
    }

    @Test
    @WithMockUser(username = "Parham", roles = {"MANAGER"})
    public void saveEmployee() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(post("/employees/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", request.getParameterValues("firstname"))
                        .param("lastName", request.getParameterValues("lastname"))
                        .param("email", request.getParameterValues("email")))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "redirect:/employees/list");

        Optional<Employee> verifyEmployee = employeeRepository.findById(1);

        assertNotNull(verifyEmployee, "Employee should be found");
    }

    @Test
    @WithMockUser(username = "Parham", roles = {"MANAGER"})
    public void saveEmployeeWhichHasBlankFieldError() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(post("/employees/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", "")
                        .param("lastName", request.getParameterValues("lastname"))
                        .param("email", request.getParameterValues("email")))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "employees/employee-form");

        Optional<Employee> verifyEmployee = employeeRepository.findById(1);

        assertNotNull(verifyEmployee, "Employee should be found");
    }


    @Test
    @WithMockUser(username = "Parham", roles = {"ADMIN"})
    public void deleteEmployeeHttpRequest() throws Exception {

        assertTrue(employeeRepository.findById(3).isPresent());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("employeeId", "3"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "redirect:/employees/list");

        assertFalse(employeeRepository.findById(3).isPresent());
    }



    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute("DELETE FROM employee");
    }
}
