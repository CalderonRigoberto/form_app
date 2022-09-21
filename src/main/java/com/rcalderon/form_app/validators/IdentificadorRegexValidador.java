package com.rcalderon.form_app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/// Se implementa interfaz y se hace la sobrescritura de metodo isValid
public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")) {
            return true;
        }
        return false;
    }

}
