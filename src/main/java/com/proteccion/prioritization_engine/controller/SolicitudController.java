package com.proteccion.prioritization_engine.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proteccion.prioritization_engine.domain.Solicitud;
import com.proteccion.prioritization_engine.service.SolicitudService;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {
    
    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    @PostMapping
    public Solicitud crearSolicitud(@RequestBody Solicitud solicitud) {
        return service.crear(solicitud);
    }

    @GetMapping
    public List<Solicitud> listarTodas() {
        return service.listar();
    }

    @GetMapping("/priorizadas")
    public List<Solicitud> listarSolicitudes() {
        return service.listarOrdenadasPorPrioridad();
    }



}
