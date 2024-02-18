package com.microservicios.expediente.dto;

import java.util.Date;

import com.microservicios.ciudadano.dto.CiudadanoRequest;
import com.microservicios.expediente.model.Expediente;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExpedienteRequest {

    @NotEmpty(message = "El DNI no puede ser nulo")
    @NotNull(message = "El DNI no puede ser nulo")
    @Size(max = 9, message = "El DNI no puede superar los 9 caracteres")
    private String dninie;
    @Size(max = 80, message = "Las notas no pueden superar los 80 caracteres")
    private String notas;
    @NotNull(message = "El tipo de prestación no puede ser nulo")
    @Min(value = 1, message = "El tipo de prestación debe ser 1, 2 o 3")
    @Max(value = 3, message = "El tipo de prestación debe ser 1, 2 o 3")
    private Integer tipoPrestacion;
    @NotNull(message = "El ciudadano no puede ser nulo")
    private Long ciudadanoId;

    public Expediente toExpediente() {
        Expediente expediente = new Expediente();
        expediente.setNotas(notas);
        expediente.setCreateAt(new Date());
        expediente.setTipoPrestacion(tipoPrestacion);
        expediente.setDNI(dninie);
        expediente.setCiudadanoId(ciudadanoId);
        return expediente;
    }

    public Expediente toExpediente(Expediente expediente) {
        expediente.setNotas(notas);
        expediente.setTipoPrestacion(tipoPrestacion);
        expediente.setDNI(dninie);
        expediente.setCiudadanoId(ciudadanoId);
        return expediente;
    }
}
