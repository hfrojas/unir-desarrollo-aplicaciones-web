package com.sistema.peajes.rutas.servicio.gestion.rutas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {
    private Long id;
    private String nombre;
    private Long ciudadOrigenId;
    private Long ciudadDestinoId;
}
