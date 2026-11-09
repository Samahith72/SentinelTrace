package com.sentineltrace.project.mapper;

import org.springframework.stereotype.Component;

import com.sentineltrace.project.dto.request.ProjectRequestDTO;
import com.sentineltrace.project.dto.response.ProjectResponseDTO;
import com.sentineltrace.project.entity.Project;

@Component 
public class ProjectMapper {

    public Project toEntity(ProjectRequestDTO projectRequestDTO){
        Project project = new Project();

        project.setName(projectRequestDTO.getName());
        project.setDescription(projectRequestDTO.getDescription());
        project.setRepositoryUrl(projectRequestDTO.getRepositoryUrl());
        project.setLanguage(projectRequestDTO.getLanguage());

        return project;
    }

    public ProjectResponseDTO toResponseDTO (Project project){
        return new ProjectResponseDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getRepositoryUrl(),
            project.getLanguage(),
            project.getCreatedAt()
        );
    }

}
