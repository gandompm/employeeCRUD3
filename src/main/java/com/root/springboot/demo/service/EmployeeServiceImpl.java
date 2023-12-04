package com.root.springboot.demo.service;



import com.root.springboot.demo.dao.EmployeeRepository;
import com.root.springboot.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        this.employeeRepository = theEmployeeRepository;
    }

    public List<Employee> findAll() {
        return this.employeeRepository.findAllByOrderByLastNameAsc();
    }

    public Employee findById(int theId) {
        Optional<Employee> result = this.employeeRepository.findById(theId);
        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = (Employee)result.get();
            return theEmployee;
        } else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
    }

    public void save(Employee theEmployee) {
        this.employeeRepository.save(theEmployee);
    }

    public void deleteById(int theId) {
        this.employeeRepository.deleteById(theId);
    }
}
