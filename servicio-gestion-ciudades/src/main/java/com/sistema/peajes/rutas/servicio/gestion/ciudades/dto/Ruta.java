package com.sistema.peajes.rutas.servicio.gestion.ciudades.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ruta {

    private Long id;
    private String nombre;
    private Long ciudadOrigenId;
    private Long ciudadDestinoId;
    private Double distancia;
    private String ciudadOrigenNombre;
    private String ciudadDestinoNombre;

}
