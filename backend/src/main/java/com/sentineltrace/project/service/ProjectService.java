package com.sentineltrace.project.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sentineltrace.common.exception.ResourceNotFoundException;
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

    public ProjectResponseDTO getProjectById(Long id){
         Project project  = projectRepository.findById(id).orElseThrow( ()->new ResourceNotFoundException(
                "Project not found with Id: "+ id
         ));

         return projectMapper.toResponseDTO(project);
    }

    public void deleteProjectById(Long id){
        projectRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException(
            "Project not found with Id: "+ id
        ));
        projectRepository.deleteById(id);

    }

    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO updatingProject){
        Project existingProject = projectRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException(
            "Project not found with Id: "+ id
        ));

        
        existingProject.setName(updatingProject.getName());
        existingProject.setDescription(updatingProject.getDescription());
        existingProject.setLanguage(updatingProject.getLanguage());
        existingProject.setRepositoryUrl(updatingProject.getRepositoryUrl());


        Project savedProject = projectRepository.save(existingProject);
        return projectMapper.toResponseDTO(savedProject);
    }

}
