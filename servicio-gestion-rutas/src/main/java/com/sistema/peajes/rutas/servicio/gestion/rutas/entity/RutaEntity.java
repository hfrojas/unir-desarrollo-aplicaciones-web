package com.sistema.peajes.rutas.servicio.gestion.rutas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rutas")
public class RutaEntity {

    @Id
    private Long id;
    private String nombre;
    private Long ciudadOrigenId;
    private Long ciudadDestinoId;

}
