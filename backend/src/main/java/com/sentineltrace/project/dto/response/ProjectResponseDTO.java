package com.sentineltrace.project.dto.response;

import java.time.LocalDateTime;

public class ProjectResponseDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final String repositoryUrl;
    private final String language;
    private final LocalDateTime createdAt;

    public ProjectResponseDTO(
        Long id,
        String name,
        String description,
        String repositoryUrl,
        String language,
        LocalDateTime createdAt
    ){
        this.id = id;
        this.description = description;
        this.name = name;
        this.repositoryUrl = repositoryUrl;
        this.language = language;
        this.createdAt = createdAt;
        
    }

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


}
