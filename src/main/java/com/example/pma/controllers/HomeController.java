package com.example.pma.controllers;

import com.example.pma.dto.ChartData;
import com.example.pma.dto.EmployeeProject;
import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.services.EmployeeService;
import com.example.pma.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {


    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectRepository proRepo;
    @Autowired
    EmployeeRepository empRepo;



    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        Map<String, Object> map = new HashMap<>();



        // we are querying the database for projects
        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList", projects);

        // we are querying the database for employees
        List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
        model.addAttribute("employeesListProjectCnt", employeesProjectCnt);

        List<ChartData> projectData = proRepo.getProjectStatus();

        //Let's convert projectData object into a json structure for use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        // [["NOT-STARTED", 1], ["IN-PROGRESS", 2], ["COMPLETED", 1]]

        model.addAttribute("projectStatusCnt", jsonString);

        return "main/home";
    }

    @RequestMapping("/search")
    public String viewSearchResult(Model model, @Param("keyword") String keyword) {
        List<Employee> listEmployees = employeeService.listAll(keyword);
        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("keyword", keyword);

        List<Project> listProjects = projectService.listAll(keyword);
        model.addAttribute("listProjects", listProjects);

        return "search/found";
    }


}
