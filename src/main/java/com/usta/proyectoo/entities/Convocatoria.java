package com.usta.proyectoo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "CONVOCATORIA")
public class Convocatoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convocatoria")
    private Long idConvocatoria;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_cierre", nullable = false)
    private Date fechaCierre;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Boolean estado;

}
