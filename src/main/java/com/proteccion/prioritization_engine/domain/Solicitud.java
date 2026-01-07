package com.proteccion.prioritization_engine.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoSolicitud tipoSolicitud;

    private int prioridadManual;

    private LocalDateTime fechaCreacion;

    private String usuario;

    protected Solicitud() {}

    public Solicitud(TipoSolicitud tipoSolicitud, int prioridadManual, String usuario) {
        this.tipoSolicitud = tipoSolicitud;
        this.prioridadManual = prioridadManual;
        this.fechaCreacion = LocalDateTime.now(); // Acá establecemos de forma automatica la fecha al crear la solicitud
        this.usuario = usuario;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public int getPrioridadManual() {
        return prioridadManual;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getUsuario() {
        return usuario;
    }

    
}
