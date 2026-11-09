package com.sentineltrace.project.dto.request;

import com.sentineltrace.project.validation.ValidRepositoryUrl;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectRequestDTO {

    @NotBlank
    @Size (max = 100) 
    private String name;

    @Size (max = 1000)
    private String description;

    @ValidRepositoryUrl 
    private String repositoryUrl;

    @NotBlank
    @Size (max = 50)
    private String language;

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

    

}
