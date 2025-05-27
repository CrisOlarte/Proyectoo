package com.usta.proyectoo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Table(name = "STARTUP")
public class Startup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_startup")
    private Long idStartup;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String vision;

    @Column(columnDefinition = "TEXT")
    private String mision;

    @Column(length = 100)
    private String sector;

    @Column(length = 100)
    private String subsector;

    @Column(name = "etapa_desarrollo", length = 100)
    private String etapaDesarrollo = "IDEA";

    @Column(name = "modelo_negocio", columnDefinition = "TEXT")
    private String modeloNegocio;

    @Column(name = "mercado_objetivo", columnDefinition = "TEXT")
    private String mercadoObjetivo;

    @Column(name = "propuesta_valor", columnDefinition = "TEXT")
    private String propuestaValor;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "sitio_web", length = 255)
    private String sitioWeb;

    @Column(name = "redes_sociales", columnDefinition = "jsonb")
    private String redesSociales; // Puede usar Map<String, String> con ayuda de un convertidor si deseas

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "estado_participacion", length = 50)
    private String estadoParticipacion = "INSCRITA";

    @ManyToOne
    @JoinColumn(name = "id_emprendedor", nullable = false)
    private Usuario emprendedor;

    @ManyToOne
    @JoinColumn(name = "id_convocatoria", nullable = false)
    private Convocatoria convocatoria;
}
