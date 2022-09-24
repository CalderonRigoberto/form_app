package com.rcalderon.form_app.models;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.rcalderon.form_app.validators.IdentificadorRegex;
import com.rcalderon.form_app.validators.RequeridoValidator;

public class Usuario {

    /// @Past para dateformat, tiene que ser una fecha en el pasado
    /// @Future tiene que ser una fecha en el futuro a partir de hoy
    /// Clase messages.properties es interpretado
    /// para sobrescribir los mensajes de error con las notaciones de @Validation

    /// Todas las propiedades de validación tienen un atributo para
    /// personalizar el mensaje cuando no se cumple de manera correcta
    /// el campo.

    // Distinto de null y no vacío
    // Patron de expresion regular
    // @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}", message = "No
    // cumple con los caracteres debidos")
    @IdentificadorRegex
    private String idUsuario;

    @NotEmpty
    @Size /// Unicamente para cadenas de texto
    private String username;
    @NotBlank(message = "El password no puede contener espacios vacíos")
    private String password;
    @NotEmpty
    @Email(message = "Ingresa un email válido") // Válida que sea en formato email
    private String email;
    // Para objetos, sirve esta validación
    // _> int -> primitivo
    @NotNull
    @Min(5)
    @Max(5000)
    private Integer cuenta;

    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @NotNull
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    // @NotEmpty
    @RequeridoValidator
    private String nombre;
    // @NotEmpty
    @RequeridoValidator
    private String apellido;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
