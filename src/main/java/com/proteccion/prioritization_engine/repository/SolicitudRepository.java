package com.proteccion.prioritization_engine.repository;

import com.proteccion.prioritization_engine.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    
}
