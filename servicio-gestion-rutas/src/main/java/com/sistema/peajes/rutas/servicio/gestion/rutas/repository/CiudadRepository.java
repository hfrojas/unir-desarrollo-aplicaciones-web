package com.sistema.peajes.rutas.servicio.gestion.rutas.repository;

import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {

    @Query(value = "SELECT * FROM ciudad WHERE nombre LIKE %?1%", nativeQuery = true)
    List<CiudadEntity> findByNameLike(String nombre);

}
