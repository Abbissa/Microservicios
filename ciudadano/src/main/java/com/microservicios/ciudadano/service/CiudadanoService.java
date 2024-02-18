package com.microservicios.ciudadano.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.ciudadano.dto.CiudadanoRequest;
import com.microservicios.ciudadano.model.Ciudadano;
import com.microservicios.ciudadano.repository.CiudadanoRepository;

@Service
public class CiudadanoService {

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    public Ciudadano altaCiudadano(CiudadanoRequest ciudadano) {

        return ciudadanoRepository.save(ciudadano.toCiudadano());
    }

    public Ciudadano getCiudadano(Long ciudadanoId) {
        return ciudadanoRepository.findById(ciudadanoId).orElse(null);
    }
}
