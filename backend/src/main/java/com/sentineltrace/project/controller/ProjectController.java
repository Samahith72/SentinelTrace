package com.sentineltrace.project.controller;

import com.sentineltrace.project.repository.ProjectRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sentineltrace.project.entity.Project;
import com.sentineltrace.project.service.ProjectService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController 
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository){
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @PostMapping()
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project createdProject = projectService.createdProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping()
    public ResponseEntity<List<Project>> getAllProject(){
        List<Project> allProject = projectService.getAllProjects();
        return ResponseEntity.ok(allProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id){
       return projectService.getProjectById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable Long id){
        boolean isDeleted = projectService.deleteProjectById(id);
        if(isDeleted == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project doesn't exist");
        }

        return  ResponseEntity.status(HttpStatus.OK).body("Project with id: "+ id +" Deleted Successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseEntity<String> updateProject(@PathVariable Long id, @RequestBody Project project){
        boolean updateProject = projectService.updateProject(id, project);
        if(!updateProject){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with id: "+ id +" doesn't exist");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Project with id: "+ id +" updated successfully!");
    }
    
    

}
