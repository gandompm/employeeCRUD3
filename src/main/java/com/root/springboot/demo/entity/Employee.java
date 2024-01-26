package com.root.springboot.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "employee"
)
public class Employee {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id"
    )
    private int id;
    @Size(min=3, message = "First name must be at least 3 characters long")
    @NotNull(message = "First name is required")
    @Column(
            name = "first_name"
    )
    private String firstName;
    @NotNull(message = "Last name is required")
    @Size(min=3, message = "Last name must be at least 3 characters long")
    @Column(
            name = "last_name"
    )
    private String lastName;
    @NotNull(message = "Email address is required")
    @Email(message = "Please enter a valid email address")
    @Column(
            name = "email"
    )
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "category",
            columnDefinition = "category_enum")
    private CategoryEnum category;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinColumn(name="employee_detail_id")
    private EmployeeDetail employeeDetail;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Task> tasks = new ArrayList<>();


    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String email, CategoryEnum category) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.category = category;
    }

    public Employee(String firstName, String lastName, String email, CategoryEnum category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Employee [id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + "]";
    }

    public EmployeeDetail getEmployeeDetail() {
        return employeeDetail;
    }

    public void setEmployeeDetail(EmployeeDetail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
