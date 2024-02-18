package com.microservicios.expediente.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.expediente.dto.ExpedienteAndCitizenRequest;
import com.microservicios.expediente.dto.ExpedienteRequest;
import com.microservicios.expediente.model.Expediente;
import com.microservicios.expediente.services.ExpedienteService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/expediente")
public class ExpedienteController {

    @Autowired
    private ExpedienteService expedienteService;

    @GetMapping()
    public List<Expediente> findAll() {
        return expedienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expediente> gExpediente(@PathVariable("id") Long id) {
        Expediente entity = expedienteService.findById(id);
        if (entity == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(entity);
    }

    @PostMapping("")
    public ResponseEntity<?> postExpediente(@Valid @RequestBody ExpedienteAndCitizenRequest entity,
            BindingResult result) {

        if (result.hasErrors()) {
            String err = errores(result);
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(err);
        }

        return expedienteService.saveCiudadanoAndExpediente(entity);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id,
            @Valid @RequestBody ExpedienteRequest entity,
            BindingResult result) {

        if (result.hasErrors()) {
            String err = errores(result);
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(err);
        }

        return expedienteService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeExpediente(@PathVariable("id") Long id) {

        expedienteService.delete(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/buscarPorDNI/{dni}")
    public ResponseEntity<List<Expediente>> getExpedientesPorDNI(@PathVariable String dni) {

        List<Expediente> expedientes = expedienteService.findByDNI(dni);
        if (expedientes == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(expedientes);
    }

    @GetMapping("/buscarPorTipoPrestacion/{param}")
    public ResponseEntity<List<Expediente>> getExpedientesPorTipoPrestacion(@PathVariable String param) {

        return ResponseEntity.ok(expedienteService.findByTipoPrestacion(Integer.parseInt(param)));
    }

    private static String errores(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"errores\": [ ");

        for (ObjectError error : result.getAllErrors()) {

            sb.append("\"error\": \"").append(error.getDefaultMessage())
                    .append("\", ");
        }

        // Eliminar la coma extra después del último error
        if (!result.getAllErrors().isEmpty()) {
            sb.deleteCharAt(sb.length() - 2);
        }

        sb.append("]");

        return sb.toString();
    }
}
