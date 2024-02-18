package com.microservicios.ciudadano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ciudadano {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CIUDADANO_ID", columnDefinition = "NUMBER(10,0)")
    private Long ciudadanoId;
    @Column(name = "NOMBRE", columnDefinition = "VARCHAR2(20)")
    private String nombre;
    @Column(name = "APELLIDO1", columnDefinition = "VARCHAR2(20)")
    private String apellido1;
    @Column(name = "APELLIDO2", columnDefinition = "VARCHAR2(20)")
    private String apellido2;
    @Column(name = "DNINIE", columnDefinition = "VARCHAR2(20)")
    private String dninie;
    @Column(name = "EMAIL", columnDefinition = "VARCHAR2(20)")
    private String email;

}
