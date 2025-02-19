package com.evento.app.web.dto.validator;

import com.evento.app.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationDto user = (UserRegistrationDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password is required");

        if (user.getUsername().length() < 4 || user.getUsername().length() > 35) {
            errors.rejectValue("username", "username.size", "Username must be between 4 and 35 characters");
        }

        if (!user.getEmail().contains("@")) {
            errors.rejectValue("email", "email.format", "Invalid email format");
        }

        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "password.size", "Password must be at least 6 characters long");
        }
    }
}