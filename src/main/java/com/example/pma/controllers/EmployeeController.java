package com.example.pma.controllers;

import com.example.pma.entities.Employee;
import com.example.pma.dao.EmployeeRepository;
import com.example.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    // this is for decoupling Repositories from Controllers
    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayEmployee(Model model){
        List<Employee> employees = empService.getAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){
        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(@Valid Employee employee, Errors errors, Model model){

        if(errors.hasErrors())
            return "employees/new-employee";


        // this should handle saving to the database..
        empService.save(employee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("id") long theId, Model model){
        Employee theEmp = empService.findByEmployeeId(theId);
        model.addAttribute("employee", theEmp); //this displays the actual form
        return "employees/new-employee";
    }

    @GetMapping ("/delete")
    public String deleteEmployee(@RequestParam("id") Long theId, Model model){
        Employee theEmp = empService.findByEmployeeId(theId);
        empService.delete(theEmp);
        return "redirect:/employees";
    }

}
