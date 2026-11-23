package com.sentineltrace.project.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;



import org.junit.jupiter.api.Test;

import com.sentineltrace.project.dto.request.ProjectRequestDTO;
import com.sentineltrace.project.dto.response.ProjectResponseDTO;
import com.sentineltrace.project.entity.Project;

public class ProjectMapperTest {

    private final ProjectMapper projectMapper = new ProjectMapper();

    @Test 
    void shouldConvertRequestDtoToEntity(){
        
        //Arrange: Prepare input data
        ProjectRequestDTO request = new ProjectRequestDTO();

        request.setName("SentinelTrace");
        request.setDescription("Software supply chain security framework");
        request.setRepositoryUrl("https://github.com/example/sentineltrace");
        request.setLanguage("Java");

        //Act: Execute the method
        Project project = projectMapper.toEntity(request);

        //Assert: Verify the result
        assertEquals("SentinelTrace", project.getName());
        assertEquals("Software supply chain security framework", project.getDescription());
        assertEquals("https://github.com/example/sentineltrace", project.getRepositoryUrl());
        assertEquals("Java", project.getLanguage());

        assertNull(project.getCreatedAt());
        assertNull(project.getId());

    }

    @Test 
    void shouldConvertEntityToResponseDTO(){
        //Arrange: create the input entity
        Project project = new Project();

        project.setName("SentinelTrace");
        project.setDescription("Software supply chain security framework");
        project.setRepositoryUrl("https://github.com/example/sentineltrace");
        project.setLanguage("Java");

        //Act: Execute the method
        ProjectResponseDTO response = projectMapper.toResponseDTO(project);

        //Assert: verify the output
        assertEquals(project.getName(), response.getName());
        assertEquals(project.getDescription(), response.getDescription());
        assertEquals(project.getLanguage(), response.getLanguage());
        assertEquals(project.getRepositoryUrl(), response.getRepositoryUrl());
    }

}
