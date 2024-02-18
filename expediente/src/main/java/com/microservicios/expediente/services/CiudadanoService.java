package com.microservicios.expediente.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicios.ciudadano.dto.CiudadanoRequest;
import com.microservicios.ciudadano.model.Ciudadano;

@Service
public class CiudadanoService {

    private final String serviceUrl = "http://localhost:8082/ciudadano/altaCiudadano";

    public Long save(CiudadanoRequest entity) {

        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<CiudadanoRequest> request = new HttpEntity<>(entity);

            ResponseEntity<?> ciudadanoReq = restTemplate.postForEntity(serviceUrl, request, Object.class);

            if (ciudadanoReq.getStatusCode() != HttpStatus.CREATED) {
                return null;
            }
            Number id = (Number) ciudadanoReq.getBody();
            return id.longValue();

        } catch (Exception e) {

            e.getMessage();
            return null;

        }
    }

    public Ciudadano findById(Long id) {

        try {

            // get ciudadano by id with request param
            RestTemplate restTemplate = new RestTemplate();

            Map<String, Long> params = new HashMap<>();

            params.put("id", id);
            ResponseEntity<Ciudadano> ciudadanoReq = restTemplate
                    .getForEntity("http://localhost:8082/ciudadano/consultarCiudadano?ciudadanoId=  {id}",
                            Ciudadano.class, params);

            if (ciudadanoReq.getStatusCode() != HttpStatus.OK) {
                return null;
            }

            return ciudadanoReq.getBody();

        } catch (Exception e) {

            e.getMessage();
            return null;

        }
    }
}
