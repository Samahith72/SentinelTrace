package com.sentineltrace.project.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.sentineltrace.project.entity.Project;
import com.sentineltrace.project.repository.ProjectRepository;

@DataJpaTest 
@ActiveProfiles("test")
public class ProjectSpecificationTest {

    @Autowired 
    private ProjectRepository projectRepository;

    @BeforeEach 
    void setUp(){
        projectRepository.deleteAll();

        projectRepository.saveAll(List.of(
            createProject(
                "SentinelTrace",
                "Java",
                "https://github.com/example/sentineltrace"
            ),
            createProject(
                "Mini Sentry",
                "Java",
                "https://github.com/example/minisentry"
            ),
            createProject(
                "Web Scanner",
                "JavaScript",
                "https://github.com/example/webscanner"
            )
        ));

        projectRepository.flush();
    }

    @Test 
    void shouldFilterProjectsByNameIgnoringCase(){
        List<Project> results = projectRepository.findAll(
            ProjectSpecification.filterProjects("sentinel", null)
        );

        assertEquals(1, results.size());
        assertEquals("SentinelTrace", results.get(0).getName());
    }

    @Test 
    void shouldFilterProjectsByLanguageIgnoringCase(){
        List<Project> results = projectRepository.findAll(
            ProjectSpecification.filterProjects(null,"java")
        );

        assertEquals(2, results.size());
    }

    @Test
    void shouldReturnAllProjectsWhenFiltersAreTogether(){
        List<Project> results = projectRepository.findAll(
            ProjectSpecification.filterProjects("sentineltrace", "java")
        );

        assertEquals(1, results.size());
        assertEquals("SentinelTrace", results.get(0).getName());
    }


    @Test 
    void shouldReturnAllProjectsWhenFiltersAreEmpty(){
        List<Project> results = projectRepository.findAll(
            ProjectSpecification.filterProjects(null, null)
        );

        assertEquals(3, results.size());
    }

    private Project createProject (
        String name,
        String language,
        String repositoryUrl
    ){
        Project project = new Project();
        project.setName(name);
        project.setDescription("Test Project");
        project.setRepositoryUrl(repositoryUrl);
        project.setLanguage(language);
        return project;
    }


}
