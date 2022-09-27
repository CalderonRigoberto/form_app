package com.rcalderon.form_app.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.rcalderon.form_app.editors.NombreMayusculaEditor;
import com.rcalderon.form_app.models.Usuario;
import com.rcalderon.form_app.validators.UserValid;

@Controller
/// Notación @SessionAtributte ayuda a mantener el estado en la sesión http
@SessionAttributes("user")
public class FormController {
    @Autowired
    private UserValid userValid;

    // Se inyecta directamente, ya que se uso una clase personalizada
    /// Lo que hace es instanciar el validador
    /// por debajo maneja interceptores
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Establece un validator pero para añadir es add
        binder.addValidators(userValid);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false); // Define si el analizador es estricto o tolerante
        // Custom editor forma parte de spring
        // Se mando el tipo requerido
        // Y dentro del custom editor se manda nuestro formato no permitiendo vacíos
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(format, false));
        binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
    }

    @ModelAttribute("paises")
    public List<String> piases() {
        return Arrays.asList("Suecia", "Islandia", "Mexico", "España");
    }

    @GetMapping({ "/form", "/", "" })
    public String form(Model model) {
        Usuario usuario = new Usuario();

        model.addAttribute("user", usuario);
        return "form";
    }

    /// No es necesario colocar la notación @RequestParam,
    /// debido a que el formulario de la vista hace el mapeo automatico
    /// porque las propiedades son iguales al POJO y lo hace por medio
    /// sus getters and setters

    /// La notación @Valid sirve para usar implemenatciones de validación
    /// predeterminadas.

    /// La interface BindingResult, siempre debe ir después de el POJO,
    /// o formulario a evaluar con la notación @Valid

    /// @ModelAtributte convierte el objecto en atributo del modelo,
    /// en este caso se puede usar la instancia del objecto para mandarlo en el
    /// BindingResult con el manejo de errores
    @PostMapping("/form")
    public String processForm(

            @Valid @ModelAttribute("user") Usuario user,
            BindingResult results,
            Model model,
            SessionStatus satus) {
        /// Implementando validación a través del metodo de manera explicita
        // userValid.validate(user, results);
        if (results.hasErrors()) {
            /// Se hace un map con los errores por campo
            /**
             * Map<String, String> getErrors = new HashMap<>();
             * results.getFieldErrors().forEach(
             * element -> {
             * getErrors.put(element.getField(),
             * "El campo ".concat(element.getField()).concat(" es requerido"));
             * });
             */
            // Retorna los errores
            return "form";
        }

        /// Si no se cumple la condición, entonces retorna usuario en la vista
        /// result
        model.addAttribute("user", user);
        satus.setComplete();
        return "result";

    }
}
