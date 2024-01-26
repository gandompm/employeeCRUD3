package com.root.springboot.demo.service;

import com.root.springboot.demo.entity.CategoryEnum;
import com.root.springboot.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);

    List<Employee> findByName(String name);

    List<Employee> findAllInCategory(CategoryEnum category);
}
