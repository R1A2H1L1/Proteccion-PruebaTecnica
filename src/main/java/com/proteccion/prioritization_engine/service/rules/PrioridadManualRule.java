package com.proteccion.prioritization_engine.service.rules;

import com.proteccion.prioritization_engine.domain.Solicitud;
import org.springframework.stereotype.Component;

@Component
public class PrioridadManualRule implements PrioritizationRule {

    @Override
    public int apply(Solicitud solicitud) {
        return solicitud.getPrioridadManual() * 10;
    }
}
