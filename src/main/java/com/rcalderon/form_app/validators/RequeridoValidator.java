package com.rcalderon.form_app.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
/// Crear notación 

@Constraint(validatedBy = Requerido.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface RequeridoValidator {
    String message() default "El campo es requerido para el registro";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
