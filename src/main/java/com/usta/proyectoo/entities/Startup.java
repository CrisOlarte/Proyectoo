package com.usta.proyectoo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@Data
@Entity
@Table(name = "STARTUP")
public class Startup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_startup")
    private Long idStartup;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sector", nullable = false)
    private String sector;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @NotNull
    @Column(name = "valoracion", nullable = false)
    private Double valoracion;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion", nullable = false)
    private java.util.Date fechaCreacion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToMany(mappedBy = "startups", fetch = FetchType.LAZY)
    private Collection<Usuario> usuarios = new ArrayList<>();
}
