package com.microservicios.ciudadano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicios.ciudadano.model.Ciudadano;

@Repository
public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long> {

}
