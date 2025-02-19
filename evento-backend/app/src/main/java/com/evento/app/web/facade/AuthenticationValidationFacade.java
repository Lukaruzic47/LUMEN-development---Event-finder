package com.evento.app.web.facade;

import com.evento.app.web.dto.UserLoginDto;
import com.evento.app.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class AuthenticationValidationFacade {

    private final List<Validator> validators;

    public AuthenticationValidationFacade(List<Validator> validators) {
        this.validators = validators;
    }

    public void validateUserRegistration(UserRegistrationDto userRegistrationDto, BindingResult bindingResult) {
        validate(userRegistrationDto, bindingResult);
    }

    public void validateUserLogin(UserLoginDto userLoginDto, BindingResult bindingResult) {
        validate(userLoginDto, bindingResult);
    }

    private void validate(Object object, BindingResult bindingResult) {
        for (Validator validator : validators) {
            if (validator.supports(object.getClass())) {
                validator.validate(object, bindingResult);
            }
        }
    }
}