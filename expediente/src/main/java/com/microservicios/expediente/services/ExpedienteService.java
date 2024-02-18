package com.microservicios.expediente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservicios.ciudadano.dto.CiudadanoRequest;
import com.microservicios.expediente.dto.ExpedienteAndCitizenRequest;
import com.microservicios.expediente.dto.ExpedienteRequest;
import com.microservicios.expediente.model.Expediente;
import com.microservicios.expediente.repositories.ExpedienteRepository;

@Service
public class ExpedienteService {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private CiudadanoService ciudadanoService;

    public List<Expediente> findAll() {
        return expedienteRepository.findAll();
    }

    public Expediente findById(Long id) {
        if (id == null)
            return null;
        return expedienteRepository.findById(id).get();
    }

    public Expediente save(Expediente entity) {

        if (entity.getId() != null && expedienteRepository.existsById(entity.getId())) {
            return null;
        }
        return expedienteRepository.save(entity);
    }

    public ResponseEntity<?> update(Long id, ExpedienteRequest entity) {

        if (ciudadanoService.findById(entity.getCiudadanoId()) == null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("¡Error! El ciudadano no existe");
        }

        Optional<Expediente> expQuery = expedienteRepository.findById(id);
        if (!expQuery.isPresent())
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("No existe el expediente con id: " + id + " para actualizar.");
        Expediente expediente = expQuery.get();

        for (Expediente e : expedienteRepository.findByDNI(entity.getDninie())) {
            if (e.getId() != id && e.getTipoPrestacion().equals(entity.getTipoPrestacion())) {
                return ResponseEntity.status(400)
                        .body("¡Error! Ya existe un expediente con el mismo DNI y tipo de prestación");
            }
        }
        entity.toExpediente(expediente);

        return ResponseEntity.status(HttpStatus.CREATED).body(expedienteRepository.save(expediente));
    }

    public void delete(Long id) {
        if (id != null && expedienteRepository.existsById(id)) {
            expedienteRepository.deleteById(id);
        }
    }

    public List<Expediente> findByDNI(String dni) {

        return expedienteRepository.findByDNI(dni);
    }

    public List<Expediente> findByTipoPrestacion(Integer tipoPrestacion) {
        return expedienteRepository.findByTipoPrestacion(tipoPrestacion);
    }

    public ResponseEntity<?> saveCiudadanoAndExpediente(ExpedienteAndCitizenRequest request) {
        Expediente expediente = request.toExpediente();
        CiudadanoRequest ciudadano = request.toCiudadano();

        for (Expediente e : expedienteRepository.findByDNI(expediente.getDNI())) {
            if (e.getTipoPrestacion().equals(expediente.getTipoPrestacion())) {
                expediente.setId(-1L);
                return ResponseEntity.status(400)
                        .body("¡Error! Ya existe un expediente con el mismo DNI y tipo de prestación");
            }
        }

        Long ciudadanoId = ciudadanoService.save(ciudadano);

        if (ciudadanoId == null) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("¡Error! No se ha podido crear el ciudadano");
        }

        expediente.setCiudadanoId(ciudadanoId);
        expediente = expedienteRepository.save(expediente);

        return ResponseEntity.status(HttpStatus.CREATED).body(expediente);

    }

}
