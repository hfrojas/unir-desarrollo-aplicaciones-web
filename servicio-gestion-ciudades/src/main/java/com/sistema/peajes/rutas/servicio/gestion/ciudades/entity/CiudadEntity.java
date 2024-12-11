package com.sistema.peajes.rutas.servicio.gestion.ciudades.entity;

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
@Table(name = "ciudad")
public class CiudadEntity {

    @Id
    private Long id;
    private String nombre;

}
