package com.sentineltrace.project.specification;

import org.springframework.data.jpa.domain.Specification;

import com.sentineltrace.project.entity.Project;

public class ProjectSpecification {

    public static Specification<Project> filterProjects(String name, String language){

        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if(name != null && !name.isBlank()){
                predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        " %" + name.toLowerCase()+ "%")
                );
            }

            if(language != null && !language.isBlank()){
                predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("language")),
                        "%" +  language.toLowerCase()+ "%")
                );
            }
            return predicate;
        };

    }

}
