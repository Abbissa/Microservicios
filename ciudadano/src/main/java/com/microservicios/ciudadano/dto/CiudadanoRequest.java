package com.microservicios.ciudadano.dto;

import com.microservicios.ciudadano.model.Ciudadano;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CiudadanoRequest {

    @NotEmpty(message = "El nombre no puede ser vacío")
    @NotNull(message = "El nombre no puede ser vacío")
    @Size(max = 20, message = "El nombre no puede superar los 20 caracteres")
    private String nombre;
    @NotEmpty(message = "El primer apellido no puede ser vacío")
    @NotNull(message = "El primer apellido no puede ser vacío")
    @Size(max = 30, message = "El primer apellido no puede superar los 30 caracteres")
    private String apellido1;
    @NotEmpty(message = "El segundo apellido no puede ser vacío")
    @NotNull(message = "El segundo apellido no puede ser nulo")
    @Size(max = 30, message = "El segundo apellido no puede superar los 30 caracteres")
    private String apellido2;
    @NotEmpty(message = "El DNI no puede ser vacío")
    @NotNull(message = "El DNI no puede ser nulo")
    @Size(max = 9, message = "El DNI no puede superar los 9 caracteres")
    private String dninie;
    @NotEmpty(message = "El email no puede ser vacío")
    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "El email no es válido")
    @Size(max = 40, message = "El email no puede superar los 40 caracteres")
    private String email;

    public Ciudadano toCiudadano() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setNombre(this.nombre);
        ciudadano.setApellido1(this.apellido1);
        ciudadano.setApellido2(this.apellido2);
        ciudadano.setDninie(this.dninie);
        ciudadano.setEmail(this.email);
        return ciudadano;
    }
}
