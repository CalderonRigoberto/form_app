package com.rcalderon.form_app.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/// lO EJECUTA EN TIEMPO DE CONSTRUCCIÓN

/// CLASE PARA CREAR ANOTACIÓN

/// @Constraint establece la clase para validars
@Constraint(validatedBy = IdentificadorRegexValidador.class)

/// Hace que se ejecute en tiempo de ejecución
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface IdentificadorRegex {

    String message() default "Identificador inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
