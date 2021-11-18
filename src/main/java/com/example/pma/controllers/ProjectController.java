package com.example.pma.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.services.EmployeeService;
import com.example.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    // this is for decoupling Repositories from Controllers
    @Autowired
    ProjectService proService;

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayProjects(Model model){
        List<Project> projects = proService.getAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model){
        Project aProject = new Project();
        List<Employee> employees = empService.getAll();
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(@Valid Project project, Errors errors, BindingResult bindingResult, Model model){
        if(errors.hasErrors())
            return "projects/new-project";
//        else if(bindingResult.hasErrors()){
//            List<String> stage = Arrays.asList("NOTSTARTED", "INPROGRESS", "COMPLETED");
//            model.addAttribute("stage", stage);
//            return "projects/new-project";
//        }

        // this should handle saving to the database..
        proService.save(project);

        // use a redirect to prevent duplicate submissions
        return "redirect:/projects";
    }

    @GetMapping("/update")
    public String updateProject(@RequestParam("id") long id, Model model){
        Project proj = proService.findByProjectId(id);
        List<Employee> employees = empService.getAll();
        model.addAttribute("allEmployees", employees);
        model.addAttribute("project", proj);

        return "projects/new-project";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("id") long id, Model model){
        Project pro = proService.findByProjectId(id);
        proService.delete(pro);
        return "redirect:/projects";
    }

}
