package com.rcalderon.form_app.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.*;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rcalderon.form_app.models.Usuario;

@Controller
public class FormController {

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
            Model model) {

        if (results.hasErrors()) {
            /// Se hace un map con los errores por campo
            Map<String, String> getErrors = new HashMap<>();
            results.getFieldErrors().forEach(
                    element -> {
                        getErrors.put(element.getField(),
                                "El campo".concat(element.getField()).concat(" es requerido"));
                    });
            // Retorna los errores
            model.addAttribute("error", getErrors);
            return "form";
        }

        /// Si no se cumple la condición, entonces retorna usuario en la vista
        /// result
        model.addAttribute("user", user);
        return "result";

    }
}
