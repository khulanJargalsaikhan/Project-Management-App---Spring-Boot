package com.example.pma.api.controllers;

import com.example.pma.dao.ProjectRepository;
import com.example.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository projRepo;

    @GetMapping
    public List<Project> getProjects(){
        return projRepo.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long id){
        return projRepo.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project project){
        return projRepo.save(project);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project project){
        return projRepo.save(project);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project partialUpdate(@PathVariable("id") Long id, @RequestBody Project patchProject){

        Project proj = projRepo.findById(id).get();

        if(patchProject.getName() != null)
            proj.setName(patchProject.getName());
        if(patchProject.getStage() != null)
            proj.setStage(patchProject.getStage());
        if(patchProject.getDescription() != null)
            proj.setDescription(patchProject.getDescription());

        return projRepo.save(proj);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        try {
            projRepo.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){

        }
    }





}
