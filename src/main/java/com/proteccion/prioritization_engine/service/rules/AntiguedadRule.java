package com.proteccion.prioritization_engine.service.rules;

import com.proteccion.prioritization_engine.domain.Solicitud;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AntiguedadRule implements PrioritizationRule {

    @Override
    public int apply(Solicitud solicitud) {
        long horas = Duration.between(
                solicitud.getFechaCreacion(),
                LocalDateTime.now()
        ).toHours();

        return (int) horas;
    }
}

