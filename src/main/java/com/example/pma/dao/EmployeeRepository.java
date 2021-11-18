package com.example.pma.dao;

import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    @Override
    List<Employee> findAll();

    @Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount \n" +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id \n" +
            "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    List<EmployeeProject> employeeProjects();

    Employee findByEmail(String value);

    Employee findByEmployeeId(long theId);

    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, e.lastName, e.email) LIKE %?1%")
    List<Employee> search(String keyword);
}
