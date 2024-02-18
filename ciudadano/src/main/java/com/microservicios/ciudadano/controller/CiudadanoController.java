package com.microservicios.ciudadano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.ciudadano.dto.CiudadanoRequest;
import com.microservicios.ciudadano.model.Ciudadano;
import com.microservicios.ciudadano.service.CiudadanoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ciudadano")
public class CiudadanoController {

    @Autowired
    private CiudadanoService ciudadanoService;

    @PostMapping("/altaCiudadano")
    public ResponseEntity<?> altaCiudadano(@Valid @RequestBody CiudadanoRequest ciudadano, BindingResult result) {
        if (result.hasErrors()) {
            String err = errores(result);
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(err);

        }
        Ciudadano ciud = ciudadanoService.altaCiudadano(ciudadano);
        Long id = ciud.getCiudadanoId();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(id);
    }

    @GetMapping("/consultarCiudadano")
    public ResponseEntity<?> getCiudadanos(@RequestParam Long ciudadanoId) {
        return ResponseEntity.ok(ciudadanoService.getCiudadano(ciudadanoId));
    }

    private static String errores(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"errores\": [ ");

        for (ObjectError error : result.getAllErrors()) {
            sb.append("\"error\": \"").append(error.getDefaultMessage()).append("\", ");
        }

        // Eliminar la coma extra después del último error
        if (!result.getAllErrors().isEmpty()) {
            sb.deleteCharAt(sb.length() - 2);
        }

        sb.append("]");

        return sb.toString();
    }

}
