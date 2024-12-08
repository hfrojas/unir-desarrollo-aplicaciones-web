package com.sistema.peajes.rutas.servicio.gestion.rutas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {

    private String nombre;
    private Long ciudadOrigenId;
    private Long ciudadDestinoId;

}
