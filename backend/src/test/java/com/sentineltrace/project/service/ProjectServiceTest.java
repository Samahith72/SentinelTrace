package com.sentineltrace.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sentineltrace.common.exception.ResourceNotFoundException;
import com.sentineltrace.project.dto.request.ProjectRequestDTO;
import com.sentineltrace.project.dto.response.ProjectResponseDTO;
import com.sentineltrace.project.entity.Project;
import com.sentineltrace.project.mapper.ProjectMapper;
import com.sentineltrace.project.repository.ProjectRepository;

@ExtendWith (MockitoExtension.class)
public class ProjectServiceTest {

    @Mock 
    private ProjectRepository projectRepository;

    @Mock 
    private ProjectMapper projectMapper;

    @InjectMocks 
    private ProjectService projectService;

    @Test 
    void shouldCreateProjectSuccessfully(){

        //Arrange: prepare test data

        ProjectRequestDTO requestDTO = new ProjectRequestDTO();
        requestDTO.setName("SentinelTrace");
        requestDTO.setDescription("software supply chain security framework");
        requestDTO.setRepositoryUrl("https://github.com/example/sentineltrace");
        requestDTO.setLanguage("Java");

        Project project = new Project();
        project.setName("SentinelTrace");
        project.setDescription("software supply chain security framework");
        project.setRepositoryUrl("https://github.com/example/sentineltrace");
        project.setLanguage("Java");

        Project savedProject = new Project();
        savedProject.setName("SentinelTrace");
        savedProject.setDescription("software supply chain security framework");
        savedProject.setRepositoryUrl("https://github.com/example/sentineltrace");
        savedProject.setLanguage("Java");

        ProjectResponseDTO expectedResponse = new ProjectResponseDTO(
            1L,
            "SentinelTrace",
            "software supply chain security framework" ,
            "https://github.com/example/sentineltrace",
            "Java",
            null );

            //Config mock behavior
            when(projectMapper.toEntity(requestDTO)).thenReturn(project);
            when(projectRepository.save(project)).thenReturn(savedProject);
            when(projectMapper.toResponseDTO(savedProject)).thenReturn(expectedResponse);

            //Act: execute method
            ProjectResponseDTO actualResponse = projectService.createdProject(requestDTO);

            //Assert: verify output
            assertEquals("SentinelTrace", actualResponse.getName());
            assertEquals("Java", actualResponse.getLanguage());
            assertEquals(1L, actualResponse.getId());
            assertEquals("software supply chain security framework", actualResponse.getDescription());
            assertEquals("https://github.com/example/sentineltrace", actualResponse.getRepositoryUrl());

            verify(projectRepository).save(project);

    }

    @Test 
    void shouldReturnProjectWhenIdExists(){
        //Arrange: prepare the input
        Long projectId = 1L;

        Project project = new Project();
        project.setName("SentinelTrace");
        project.setDescription("software supply chain security framework");
        project.setRepositoryUrl("https://github.com/example/sentineltrace");
        project.setLanguage("Java");

        ProjectResponseDTO expectedResponse = new ProjectResponseDTO(
            projectId,
            "SentinelTrace",
            "software supply chain security framework",
            "https://github.com/example/sentineltrace",
            "Java",
             null);
        
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectMapper.toResponseDTO(project)).thenReturn(expectedResponse);

        //Act: execute the method
        ProjectResponseDTO actualResponse = projectService.getProjectById(projectId);

        //Assert: verify the result
        assertEquals(projectId, actualResponse.getId());
        assertEquals("SentinelTrace", actualResponse.getName());
        assertEquals("Java", actualResponse.getLanguage());

        verify(projectRepository).findById(projectId);
        verify(projectMapper).toResponseDTO(project);
    }

    @Test 
    void shouldThrowExceptionWhenProjectDoesNotExist() {
        
        //Arrange: prepare input
        Long id = 99L;
        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        //Act & Assert: execute method and verify result
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
            ()-> projectService.getProjectById(id));
        
        assertEquals(
            "Project not found with Id: 99",
             exception.getMessage()
        );

        verify(projectRepository).findById(id);
    }

    @Test 
    void shouldUpdateProjectSuccessfully(){

        //Arrange: Prepare Input
        Long projectId = 1L;

        ProjectRequestDTO requestDTO = new ProjectRequestDTO();
        requestDTO.setName("Updated SentinelTrace");
        requestDTO.setDescription("Updated software supply chain security framework");
        requestDTO.setRepositoryUrl("https://github.com/example/sentineltrace");
        requestDTO.setLanguage("Java");

        Project existingProject = new Project();
        existingProject.setName("SentinelTrace");
        existingProject.setDescription("Original description");
        existingProject.setRepositoryUrl("https://github.com/example/sentineltrace");
        existingProject.setLanguage("Java");

        Project savedProject = new Project();
        savedProject.setName("Updated SentinelTrace");
        savedProject.setDescription("Updated software supply chain security framework");
        savedProject.setRepositoryUrl("https://github.com/example/sentineltrace");
        savedProject.setLanguage("Java");

        ProjectResponseDTO expectedResponse = new ProjectResponseDTO(projectId,
            "Updated SentinelTrace",
            "Updated software supply chain security framework",
            "https://github.com/example/sentineltrace",
            "Java",
             null);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(existingProject)).thenReturn(savedProject);
        when(projectMapper.toResponseDTO(savedProject)).thenReturn(expectedResponse);

        //Act
        ProjectResponseDTO actualResponse = projectService.updateProject(projectId, requestDTO);

        //Assert
        assertEquals(projectId, actualResponse.getId());
        assertEquals("Updated SentinelTrace", actualResponse.getName());
        assertEquals("Updated software supply chain security framework", actualResponse.getDescription());
        assertEquals("https://github.com/example/sentineltrace", actualResponse.getRepositoryUrl());
        assertEquals("Java", actualResponse.getLanguage());

        verify(projectRepository).findById(projectId);
        verify(projectRepository).save(existingProject);
        verify(projectMapper).toResponseDTO(savedProject);

    }


    @Test 
    void shouldThrowExceptionWhenUpdatingProjectDoesNotExist(){

        //Arrange: prepare input
        Long id = 99L;

        ProjectRequestDTO requestDTO = new ProjectRequestDTO();
        requestDTO.setName("Updated SentinelTrace");
        requestDTO.setDescription("Updated description");
        requestDTO.setRepositoryUrl("https://github.com/example/sentineltrace");
        requestDTO.setLanguage("Java");

        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        //Act & Assert: execute and verify method
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            ()-> projectService.updateProject(id, requestDTO));
        
        assertEquals("Project not found with Id: 99", exception.getMessage());

        verify(projectRepository).findById(id);
        verify(projectRepository, never()).save(any(Project.class));

    }

    @Test 
    void shouldDeleteProjectWhenExist(){

        //Arrange: prepare input
        Long projectId = 1L;
        Project project = new Project();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        projectService.deleteProjectById(projectId);

        verify(projectRepository).findById(projectId);
        verify(projectRepository).deleteById(projectId);
    }

    @Test 
    void shouldThrowExceptionWhenDeletingProjectDoesNotExist(){
        Long projectId = 99L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        //Act & Assert: execute method and verify result
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
            ()-> projectService.deleteProjectById(projectId));
        
        assertEquals(
            "Project not found with Id: 99",
             exception.getMessage()
        );

        verify(projectRepository).findById(projectId);
        verify(projectRepository, never()).deleteById(projectId);

    }
}
