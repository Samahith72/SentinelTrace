package com.sentineltrace.project.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sentineltrace.project.dto.request.ProjectRequestDTO;
import com.sentineltrace.project.dto.response.ProjectResponseDTO;
import com.sentineltrace.project.entity.Project;
import com.sentineltrace.project.mapper.ProjectMapper;
import com.sentineltrace.project.repository.ProjectRepository;

@Service 
public class ProjectService {

    private final ProjectRepository projectRepository;
    private  final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper){
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectResponseDTO createdProject(ProjectRequestDTO projectReq){
        Project project = projectMapper.toEntity(projectReq);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toResponseDTO(savedProject);
    }

    public List<ProjectResponseDTO> getAllProjects(){
        return projectRepository.findAll().stream().map(projectMapper::toResponseDTO).toList();
    }

    public Optional<ProjectResponseDTO> getProjectById(Long id){
        return projectRepository.findById(id).map(projectMapper::toResponseDTO);
    }

    public boolean deleteProjectById(Long id){
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()){
            return false;
        }
        projectRepository.delete(project.get());
        return true;
    }

    public Optional<ProjectResponseDTO> updateProject(Long id, ProjectRequestDTO updatingProject){
        Optional<Project> existingProject = projectRepository.findById(id);
        if(existingProject.isEmpty()){
            return Optional.empty();
        }

        Project project = existingProject.get();
        project.setName(updatingProject.getName());
        project.setDescription(updatingProject.getDescription());
        project.setLanguage(updatingProject.getLanguage());
        project.setRepositoryUrl(updatingProject.getRepositoryUrl());


        Project savedProject = projectRepository.save(project);
        return Optional.of(projectMapper.toResponseDTO(savedProject));
    }

}
