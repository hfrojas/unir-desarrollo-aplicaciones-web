package com.sistema.peajes.rutas.servicio.gestion.rutas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ruta")
public class RutaEntity {

    @Id
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ciudad_id_origen")
    private Long ciudadOrigenId;

    @Column(name = "ciudad_id_destino")
    private Long ciudadDestinoId;

    @Column(name = "distancia")
    private Double distancia;

    @Transient
    private String ciudadOrigenNombre;

    @Transient
    private String ciudadDestinoNombre;

}
