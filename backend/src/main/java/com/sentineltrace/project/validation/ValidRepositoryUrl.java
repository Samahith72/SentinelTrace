package com.sentineltrace.project.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = RepositoryUrlValidator.class)
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface  ValidRepositoryUrl {

    String message() default "Repository URL must be a valid HTTP or HTTPS URL";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
    

}
