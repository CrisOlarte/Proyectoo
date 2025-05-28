package com.usta.proyectoo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CONVOCATORIA")
public class Convocatoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convocatoria")
    private Long idConvocatoria;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "fecha_cierre_inscripciones")
    private LocalDate fechaCierreInscripciones;

    @Column(columnDefinition = "TEXT")
    private String requisitos;

    private BigDecimal presupuesto;

    @Column(name = "numero_plazas")
    private Integer numeroPlazas = 0;

    @Column(length = 50)
    private String estado = "ACTIVA";

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "admin_correo", length = 100)
    private String adminCorreo;

    @Column(name = "admin_nombre", length = 100)
    private String adminNombre;

    //ho,sa dsadvGYSAFD YRaf dTUgasi
}
