package com.sentineltrace.project.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sentineltrace.project.dto.request.ProjectRequestDTO;
import com.sentineltrace.project.dto.response.ProjectResponseDTO;
import com.sentineltrace.project.service.ProjectService;


@WebMvcTest (ProjectController.class)
public class ProjectControllerTest {


    @Autowired 
    private MockMvc mockMvc;

    @MockitoBean 
    private ProjectService projectService;

    private ProjectResponseDTO responseDTO(){
        return new ProjectResponseDTO(
            1L,
            "SentinelTrace",
            "Software supply chain security framework",
            "https://github.com/example/sentineltrace",
            "Java",
            LocalDateTime.of(2026, 11,23,10,0) 
        );
    }

    private String validRequestJson() {
        return """
                {
                    "name": "SentinelTrace",
                    "description": "Software supply chain security framework",
                    "repositoryUrl": "https://github.com/example/sentineltrace",
                    "language": "Java"
                }
                """;
    }

    @Test
    void shouldCreateProject() throws Exception {
        when(projectService.createdProject(any(ProjectRequestDTO.class)))
            .thenReturn(responseDTO());

        mockMvc.perform(post("/api/v1/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestJson()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("SentinelTrace"))
            .andExpect(jsonPath("$.language").value("Java"));
    }

    @Test
    void shouldGetProjectById() throws Exception {
        when(projectService.getProjectById(1L))
            .thenReturn(responseDTO());

        mockMvc.perform(get("/api/v1/projects/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("SentinelTrace"));
    }

    @Test
    void shouldUpdateProject() throws Exception {
        when(projectService.updateProject(
                eq(1L),
                any(ProjectRequestDTO.class)))
            .thenReturn(responseDTO());

        mockMvc.perform(put("/api/v1/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestJson()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("SentinelTrace"));
    }

    @Test
    void shouldDeleteProject() throws Exception {
        mockMvc.perform(delete("/api/v1/projects/1"))
            .andExpect(status().isNoContent());

        verify(projectService).deleteProjectById(1L);
    }

    @Test
    void shouldGetAllProjects() throws Exception {
        Page<ProjectResponseDTO> page = new PageImpl<>(
            List.of(responseDTO()),
            PageRequest.of(0, 10),
            1
        );

        when(projectService.getAllProjects(
                0, 10, "createdAt", "desc", null, null))
            .thenReturn(page);

        mockMvc.perform(get("/api/v1/projects")
                .param("page", "0")
                .param("size", "10")
                .param("sortBy", "createdAt")
                .param("direction", "desc"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value(1))
            .andExpect(jsonPath("$.content[0].name").value("SentinelTrace"))
            .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void shouldRejectProjectWithBlankName() throws Exception {
        String invalidRequest = """
            {
                "name": " ",
                "description": "Invalid project",
                "repositoryUrl": "https://github.com/example/project",
                "language": "Java"
            }
            """;

        mockMvc.perform(post("/api/v1/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
            .andExpect(status().isBadRequest());
    }

}
