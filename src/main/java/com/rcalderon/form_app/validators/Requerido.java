package com.rcalderon.form_app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/// Clase para validar a través de notación
public class Requerido implements ConstraintValidator<RequeridoValidator, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty() || !org.springframework.util.StringUtils.hasText(value))
            return false;
        return true;
    }

}
