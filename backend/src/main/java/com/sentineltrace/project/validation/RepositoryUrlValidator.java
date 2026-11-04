package com.sentineltrace.project.validation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RepositoryUrlValidator implements  ConstraintValidator<ValidRepositoryUrl, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null || value.isBlank()){
            return true;
        }
        try {
            URI uri = new URI(value);
            String scheme = uri.getScheme();

            //Only HTTP & HTTPS protocols are accepted
            if(scheme == null || !(scheme.toLowerCase(Locale.ROOT).equals("http") || scheme.toLowerCase(Locale.ROOT).equals("https"))){
                return false;
            }

            return uri.isAbsolute() && uri.getHost() != null && !uri.getHost().isBlank();


        } catch (URISyntaxException exception) {
            return false;
        }
    }

}
