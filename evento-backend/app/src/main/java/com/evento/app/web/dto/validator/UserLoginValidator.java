package com.evento.app.web.dto.validator;

import com.evento.app.web.dto.UserLoginDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserLoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserLoginDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserLoginDto userLogin = (UserLoginDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password is required");

        if (!userLogin.getEmail().contains("@")) {
            errors.rejectValue("email", "email.format", "Invalid email format");
        }

        if (userLogin.getPassword().length() < 6) {
            errors.rejectValue("password", "password.size", "Password must be at least 6 characters long");
        }
    }
}
