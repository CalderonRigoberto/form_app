package com.rcalderon.form_app.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rcalderon.form_app.models.Usuario;

/// Se transforma en componente para poder ser inyectado
@Component
public class UserValid implements Validator {

    /// Verifica que la clase target sea correcta
    @Override
    public boolean supports(Class<?> clazz) {
        /// Clase o entity que vamos a evaluar
        // Validar que corresponda al tipo de clase
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        /// Procesas la información necesaria
        /// y creas las validaciones especificas,
        /// puedes hacerlo de las siguientes dos maneras:

        Usuario usuario = (Usuario) target;
        /// opcion 1
        /// Utilizar ValidateUtils que contiene varias opciones
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.user.nombre");
        // opcion 2
        /**
         * if
         * (!usuario.getIdUsuario().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}"))
         * {
         * errors.rejectValue("idUsuario", "Pattern.idUsuario");
         * }
         */
    }

}
