package com.root.springboot.demo.controller;


import com.root.springboot.demo.entity.CategoryEnum;
import com.root.springboot.demo.entity.Employee;
import com.root.springboot.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        theModel.addAttribute("selectedCategory", null); // Add the selected category for display
        return "employees/list-employees";
    }

    @GetMapping("/search")
    public String searchEmployees(@RequestParam("name") String name, Model theModel) {
        List<Employee> searchResults = employeeService.findByName(name.trim());
        theModel.addAttribute("employees", searchResults);
        return "employees/list-employees";
    }

    @GetMapping("/list/category")
    public String listEmployeesByCategory(@RequestParam("category") CategoryEnum category, Model theModel) {
        List<Employee> theEmployees = this.employeeService.findAllInCategory(category);
        theModel.addAttribute("employees", theEmployees);
        theModel.addAttribute("selectedCategory", category.name()); // Add the selected category for display
        return "employees/list-employees";
    }


    @GetMapping({"/showFormForAdd"})
    public String showFormForAdd(Model theModel) {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @GetMapping({"/showFormForDetails"})
    public String showFormForDetails(@RequestParam("employeeId") int theId, Model theModel) {
        Employee theEmployee = this.employeeService.findById(theId);
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @PostMapping({"/save"})
    public String saveEmployee(
            @Valid @ModelAttribute("employee") Employee theEmployee,
            BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()){
            return "employees/employee-form";
        }
        else {
            this.employeeService.save(theEmployee);
            return "redirect:/employees/list";
        }
    }

    @GetMapping({"/delete"})
    public String delete(@RequestParam("employeeId") int theId) {

        if (employeeService.findById(theId) != null) {
            this.employeeService.deleteById(theId);
        }
        return "redirect:/employees/list";
    }
}
