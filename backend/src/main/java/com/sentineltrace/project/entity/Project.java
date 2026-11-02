package com.sentineltrace.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity 
@Table (name = "Projects")
public class Project {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column (length = 1000)
    private String description;
    
    private String repositoryUrl;
    private String language;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getRepositoryUrl() {
        return repositoryUrl;
    }
    public String getLanguage() {
        return language;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    @PrePersist 
    public void initializeCreatedAt() {
        if(createdAt == null){
            createdAt = LocalDateTime.now();
        }
    }

    

}
