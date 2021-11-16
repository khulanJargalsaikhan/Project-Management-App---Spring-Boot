package com.example.pma.dao;

import com.example.pma.dto.ChartData;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    @Override
    List<Project> findAll();

    @Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value FROM project GROUP BY stage")
    List<ChartData> getProjectStatus();

    Project findByProjectId(long id);

    @Query("SELECT p FROM Project p WHERE CONCAT(p.name, p.stage, p.description) LIKE %?1%")
    List<Project> search(String keyword);

}
