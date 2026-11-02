package com.sentineltrace.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sentineltrace.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
