package com.microservicios.expediente.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", columnDefinition = "NUMBER(10,0)")
    private Long id;
    @Column(name = "TIPO_PRESTACION", columnDefinition = "NUMBER(1,0)")
    private Integer tipoPrestacion;
    @Column(name = "NOTAS", columnDefinition = "VARCHAR2(80)")
    private String notas;
    @Column(name = "CREATE_AT", columnDefinition = "DATE")
    private Date createAt;
    @Column(name = "DNI", columnDefinition = "VARCHAR(9)")
    private String DNI;
    @Column(name = "CIUDADANO_ID", columnDefinition = "NUMBER(10,0)")
    private Long ciudadanoId;
}
