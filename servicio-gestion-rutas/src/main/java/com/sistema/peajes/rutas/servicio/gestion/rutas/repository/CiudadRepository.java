package com.sistema.peajes.rutas.servicio.gestion.rutas.repository;

import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {
}
