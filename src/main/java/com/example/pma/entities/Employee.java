package com.example.pma.entities;

import com.example.pma.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employee_seq")
    //@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    private long employeeId;

    @NotBlank
    @Size(min=2, max=50)
    private String firstName;

    @NotBlank
    @Size(min=1, max=50)
    private String lastName;

    @NotBlank
    @Email(message = "*Must give a valid email address")  //controller(client side) level validation
    @UniqueValue  //this is custom validation that we created
    private String email;

    //@Column(unique = true) //this is database level validation, and this will not work bc ddl=none in app.properties file



    //this is child side, Project is parent   --> these are all rules about relationships between parent and child
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
                fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns = @JoinColumn(name="employee_id"),
            inverseJoinColumns = @JoinColumn(name="project_id"))

    @JsonIgnore  // this ignores this particular field for serialization
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
