package com.microservicios.expediente.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ExpedienteResponse {

    private Long id;
    private Integer tipoPrestacion;
    private String notas;
    private Date createAt;
    private String DNI;
    private Long ciudadanoId;

}
