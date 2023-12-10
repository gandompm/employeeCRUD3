package com.root.springboot.demo.service;

import com.root.springboot.demo.dao.EmployeeRepository;
import com.root.springboot.demo.entity.Employee;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class EmployeeServiceTest {


    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;


    @BeforeEach
    public void setupDatabase() {
        jdbc.execute("insert into employee(id, first_name, last_name, email) " +
                "values (3, 'Parham', 'Gkar', 'parham@gmail.com')");
    }

    @Test
    public void createEmployee(){

        Employee employee = new Employee(4, "Parham", "Gandomkar", "parham2@gmail.com");
        employeeService.save(employee);

        Assertions.assertNotNull(employeeRepository.findById(4), "find employee by id");
    }

    @Test
    public void isEmployeeNullCheck() {
        assertThrows(RuntimeException.class, () -> {
            // the id that should throw an exception
            employeeService.findById(5);
        }, "find employee by id");
    }

    @Test
    public void isEmployeeNotNullCheck() {
        Assertions.assertNotNull(employeeService.findById(3), "find employee by id");
    }

    @Test
    @Transactional
    public void deleteEmployee() {

        Optional<Employee> deletedEmployee = employeeRepository.findById(3);

        assertTrue(deletedEmployee.isPresent(), "Return True");

        employeeService.deleteById(3);

        deletedEmployee = employeeRepository.findById(3);

        assertFalse(deletedEmployee.isPresent(), "Return False");
    }

    @Sql("/insertEmployees.sql")
    @Test
    public void getAllEmployees() {

        Iterable<Employee> iterableEmployees = employeeService.findAll();
        List<Employee> employees = new ArrayList<>();

        for (Employee employee : iterableEmployees) {
            employees.add(employee);
        }

        assertEquals(5, employees.size());
    }


    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute("DELETE FROM employee");
    }
}
