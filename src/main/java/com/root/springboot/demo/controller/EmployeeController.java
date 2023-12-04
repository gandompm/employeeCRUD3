package com.root.springboot.demo.controller;


import com.root.springboot.demo.entity.Employee;
import com.root.springboot.demo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping({"/employees"})
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    @GetMapping({"/list"})
    public String listEmployees(Model theModel) {
        List<Employee> theEmployees = this.employeeService.findAll();
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }

    @GetMapping({"/showFormForAdd"})
    public String showFormForAdd(Model theModel) {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @GetMapping({"/showFormForUpdate"})
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
        Employee theEmployee = this.employeeService.findById(theId);
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @PostMapping({"/save"})
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        this.employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }

    @GetMapping({"/delete"})
    public String delete(@RequestParam("employeeId") int theId) {
        this.employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }
}
