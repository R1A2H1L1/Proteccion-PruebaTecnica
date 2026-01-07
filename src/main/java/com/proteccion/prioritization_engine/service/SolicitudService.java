package com.proteccion.prioritization_engine.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proteccion.prioritization_engine.domain.Solicitud;
import com.proteccion.prioritization_engine.repository.SolicitudRepository;

@Service
public class SolicitudService {

    private final SolicitudRepository repository;
    private final PrioritizationEngine prioritizationEngine;

    public SolicitudService(SolicitudRepository repository,
                            PrioritizationEngine prioritizationEngine) {
        this.repository = repository;
        this.prioritizationEngine = prioritizationEngine;
    }

    // Crear
    public Solicitud crear(Solicitud solicitud) {
        return repository.save(solicitud);
    }

    // Listar de forma simple
    public List<Solicitud> listar() {
        return repository.findAll();
    }

    // Listar de forma priorizada
    public List<Solicitud> listarOrdenadasPorPrioridad() {
        return repository.findAll().stream()
                .sorted(
                    Comparator
                        .comparingInt(prioritizationEngine::calculatePriority)
                        .reversed()
                )
                .toList();
    }
}
