package com.sistema.peajes.rutas.servicio.gestion.rutas.repository;

import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.RutaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<RutaEntity, Long> {
}