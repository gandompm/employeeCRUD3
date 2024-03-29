package com.root.springboot.demo.dao;


import java.util.List;

import com.root.springboot.demo.entity.CategoryEnum;
import com.root.springboot.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByOrderByLastNameAsc();

    List<Employee> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);

    List<Employee> findByCategory(CategoryEnum category);
}
