package com.proteccion.prioritization_engine.service;

import com.proteccion.prioritization_engine.domain.Solicitud;
import com.proteccion.prioritization_engine.service.rules.PrioritizationRule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrioritizationEngine {

    private final List<PrioritizationRule> rules;

    public PrioritizationEngine(List<PrioritizationRule> rules) {
        this.rules = rules;
    }

    public int calculatePriority(Solicitud solicitud) {
        return rules.stream()
                .mapToInt(rule -> rule.apply(solicitud))
                .sum();
    }
}
