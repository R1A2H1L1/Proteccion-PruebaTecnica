package com.proteccion.prioritization_engine.service.rules;

import com.proteccion.prioritization_engine.domain.Solicitud;

public interface PrioritizationRule {

    int apply(Solicitud solicitud);
}

