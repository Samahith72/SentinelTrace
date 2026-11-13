package com.sentineltrace.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sentineltrace.project.dto.request.ProjectRequestDTO;
import com.sentineltrace.project.dto.response.ProjectResponseDTO;
import com.sentineltrace.project.service.ProjectService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping()
    public ResponseEntity<ProjectResponseDTO> createProject( @Valid  @RequestBody ProjectRequestDTO project){
        ProjectResponseDTO createdProject = projectService.createdProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponseDTO>> getAllProject(){
        List<ProjectResponseDTO> allProject = projectService.getAllProjects();
        return ResponseEntity.ok(allProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id){
        projectService.deleteProjectById(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id,@Valid  @RequestBody ProjectRequestDTO projectRequestDTO){
        return ResponseEntity.ok(projectService.updateProject(id, projectRequestDTO));
    }
    
    

}
