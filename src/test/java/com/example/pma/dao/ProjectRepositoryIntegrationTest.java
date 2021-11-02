package com.example.pma.dao;

import com.example.pma.PmaApplication;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.entities.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = PmaApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts={"classpath:schema.sql", "classpath:data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:drop.sql")})
public class ProjectRepositoryIntegrationTest {
    @Autowired
    ProjectRepository proRepo;

    @Test
    public void ifNewProjectSave_thenSuccess(){
        Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
        proRepo.save(newProject);

        assertEquals(5, proRepo.findAll().size());
    }




}
