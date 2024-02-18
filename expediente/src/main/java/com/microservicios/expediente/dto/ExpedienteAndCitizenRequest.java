package com.microservicios.expediente.dto;

import java.util.Date;

import com.microservicios.ciudadano.dto.CiudadanoRequest;
import com.microservicios.expediente.model.Expediente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExpedienteAndCitizenRequest {

    @NotEmpty(message = "El nombre no puede ser nulo")
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(max = 20, message = "El nombre no puede superar los 20 caracteres")
    private String nombre;
    @NotEmpty(message = "El primer apellido no puede ser nulo")
    @NotNull(message = "El primer apellido no puede ser nulo")
    @Size(max = 30, message = "El primer apellido no puede superar los 30 caracteres")
    private String apellido1;
    @NotEmpty(message = "El segundo apellido no puede ser nulo")
    @NotNull(message = "El segundo apellido no puede ser nulo")
    @Size(max = 30, message = "El segundo apellido no puede superar los 30 caracteres")
    private String apellido2;
    @NotEmpty(message = "El DNI no puede ser nulo")
    @NotNull(message = "El DNI no puede ser nulo")
    @Size(max = 9, message = "El DNI no puede superar los 9 caracteres")
    private String dninie;
    @NotEmpty(message = "El email no puede ser nulo")
    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "El email debe ser válido")
    @Size(max = 40, message = "El email no puede superar los 40 caracteres")
    private String email;
    @Size(max = 80, message = "Las notas no pueden superar los 80 caracteres")
    private String notas;
    @NotNull(message = "El tipo de prestación no puede ser nulo")
    @Min(value = 1, message = "El tipo de prestación debe ser 1, 2 o 3")
    @Max(value = 3, message = "El tipo de prestación debe ser 1, 2 o 3")
    private Integer tipoPrestacion;

    public CiudadanoRequest toCiudadano() {
        CiudadanoRequest ciudadano = new CiudadanoRequest();
        ciudadano.setNombre(nombre);
        ciudadano.setApellido1(apellido1);
        ciudadano.setApellido2(apellido2);
        ciudadano.setDninie(dninie);
        ciudadano.setEmail(email);
        return ciudadano;
    }

    public Expediente toExpediente() {
        Expediente expediente = new Expediente();
        expediente.setNotas(notas);
        expediente.setCreateAt(new Date());
        expediente.setTipoPrestacion(tipoPrestacion);
        expediente.setDNI(dninie);
        return expediente;
    }
}
