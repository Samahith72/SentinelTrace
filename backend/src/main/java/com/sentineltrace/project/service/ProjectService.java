package com.sentineltrace.project.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sentineltrace.common.exception.ResourceNotFoundException;
import com.sentineltrace.project.dto.request.ProjectRequestDTO;
import com.sentineltrace.project.dto.response.ProjectResponseDTO;
import com.sentineltrace.project.entity.Project;
import com.sentineltrace.project.mapper.ProjectMapper;
import com.sentineltrace.project.repository.ProjectRepository;
import com.sentineltrace.project.specification.ProjectSpecification;

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

    public Page<ProjectResponseDTO> getAllProjects(
                        int page, 
                        int size, 
                        String sortBy, 
                        String direction,
                        String name,
                        String language
    ){

        List<String> allowedFields = List.of("name", "language", "createdAt");

        if(!allowedFields.contains(sortBy)){
            throw new IllegalArgumentException("Invalid sorting field: "+ sortBy);
        }

        if(!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")){
            throw new IllegalArgumentException("Invalid sorting direction: "+ direction);
        }


        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Project> specification = ProjectSpecification.filterProjects(name, language);
        Page<Project> projectPage = projectRepository.findAll(specification,pageable);
        return projectPage.map(projectMapper::toResponseDTO);
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
