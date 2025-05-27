package com.usta.proyectoo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EVALUACION", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_startup", "id_evaluador", "fase_evaluacion"})
})
public class Evaluacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion")
    private Long idEvaluacion;

    @ManyToOne
    @JoinColumn(name = "id_startup", nullable = false)
    private Startup startup;

    @ManyToOne
    @JoinColumn(name = "id_evaluador", nullable = false)
    private Usuario evaluador;

    @Column(name = "fecha_evaluacion")
    private LocalDateTime fechaEvaluacion;

    private Integer puntuacionIdea;
    private Integer puntuacionEquipo;
    private Integer puntuacionViabilidad;
    private Integer puntuacionInnovacion;
    private Integer puntuacionPresentacion;
    private Integer puntuacionMercado;
    private Integer puntuacionFinanciera;

    @Column(name = "puntuacion_total")
    private Integer puntuacionTotal;

    @Column(columnDefinition = "TEXT")
    private String comentarios;

    @Column(columnDefinition = "TEXT")
    private String fortalezas;

    @Column(columnDefinition = "TEXT")
    private String debilidades;

    @Column(columnDefinition = "TEXT")
    private String recomendaciones;

    @Column(name = "fase_evaluacion", length = 50)
    private String faseEvaluacion = "PRIMERA_REVISION";

    private Boolean aprobado;

    @Column(name = "fecha_decision")
    private LocalDateTime fechaDecision;

    @Column(name = "observaciones_finales", columnDefinition = "TEXT")
    private String observacionesFinales;
}
