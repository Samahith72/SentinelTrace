package com.sentineltrace.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.sentineltrace.project.entity.Project;

@DataJpaTest
@ActiveProfiles("test")
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void shouldSaveAndFindProjectById() {
        Project project = new Project();
        project.setName("SentinelTrace");
        project.setDescription("Software supply-chain risk framework");
        project.setRepositoryUrl("https://github.com/example/sentineltrace");
        project.setLanguage("Java");

        Project savedProject = projectRepository.save(project);

        assertNotNull(savedProject.getId());

        var foundProject = projectRepository.findById(savedProject.getId());

        assertTrue(foundProject.isPresent());
        assertEquals("SentinelTrace", foundProject.get().getName());
        assertEquals("Java", foundProject.get().getLanguage());
    }
}