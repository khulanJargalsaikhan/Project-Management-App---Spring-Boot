package com.example.pma.services;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public Employee save(Employee employee){
        return empRepo.save(employee);
    }

    public List<Employee> getAll(){
        return empRepo.findAll();
    }

    public List<EmployeeProject> employeeProjects(){
        return empRepo.employeeProjects();
    }

    public Employee findByEmployeeId(long theId) {
        return empRepo.findByEmployeeId(theId);
    }

    public void delete(Employee theEmp) {
        empRepo.delete(theEmp);
    }
}
