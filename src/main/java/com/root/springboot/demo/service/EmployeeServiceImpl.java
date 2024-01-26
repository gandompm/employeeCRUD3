package com.root.springboot.demo.service;



import com.root.springboot.demo.dao.EmployeeRepository;
import com.root.springboot.demo.entity.CategoryEnum;
import com.root.springboot.demo.entity.Employee;
import com.root.springboot.demo.entity.Task;
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

    public List<Employee> findByName(String name) {
        // Assuming that the 'name' parameter can be either first name or last name
        return employeeRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(name, name);
    }

    @Override
    public List<Employee> findAllInCategory(CategoryEnum category) {
        return employeeRepository.findByCategory(category);
    }

    public void save(Employee theEmployee) {
        this.employeeRepository.save(theEmployee);
    }

    public void deleteById(int theId) {
        // Retrieve the employee
        Employee employee = employeeRepository.findById(theId).orElse(null);

        if (employee != null) {
            // Get the tasks
            List<Task> tasks = employee.getTasks();

            if (tasks != null){
                // Break association of all tasks for the employee
                for (Task task : tasks) {
                    task.setEmployee(null);
                }
            }

            this.employeeRepository.deleteById(theId);
        }
    }
}
