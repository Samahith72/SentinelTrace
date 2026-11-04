package com.sentineltrace.project.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sentineltrace.project.entity.Project;
import com.sentineltrace.project.repository.ProjectRepository;

@Service 
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public Project createdProject(Project projectReq){
        return projectRepository.save(projectReq);
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id){
        return projectRepository.findById(id);
    }

    public boolean deleteProjectById(Long id){
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()){
            return false;
        }
        projectRepository.delete(project.get());
        return true;
    }

    public boolean updateProject(Long id, Project updatingProject){
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()){
            return false;
        }

        Project existiProject = project.get();
        existiProject.setName(updatingProject.getName());
        existiProject.setDescription(updatingProject.getDescription());
        existiProject.setLanguage(updatingProject.getLanguage());
        existiProject.setRepositoryUrl(updatingProject.getRepositoryUrl());
        projectRepository.save(existiProject);
        return true;
    }

}
