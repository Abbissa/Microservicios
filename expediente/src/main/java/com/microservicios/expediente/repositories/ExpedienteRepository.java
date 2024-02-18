package com.microservicios.expediente.repositories;

import com.microservicios.expediente.model.Expediente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {

    public List<Expediente> findByDNI(String dni);

    public List<Expediente> findByTipoPrestacion(Integer tipoPrestacion);

}
