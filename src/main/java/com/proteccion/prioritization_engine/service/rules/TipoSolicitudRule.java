package com.proteccion.prioritization_engine.service.rules;

import org.springframework.stereotype.Component;

import com.proteccion.prioritization_engine.domain.Solicitud;
import com.proteccion.prioritization_engine.domain.TipoSolicitud;

@Component
public class TipoSolicitudRule implements PrioritizationRule {

    @Override
    public int apply(Solicitud solicitud) {
        return solicitud.getTipoSolicitud() == TipoSolicitud.INCIDENTE ? 50 : 0;
    }
}
