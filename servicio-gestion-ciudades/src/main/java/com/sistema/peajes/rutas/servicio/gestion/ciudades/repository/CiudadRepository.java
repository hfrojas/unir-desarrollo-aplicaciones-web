package com.sistema.peajes.rutas.servicio.gestion.ciudades.repository;

import com.sistema.peajes.rutas.servicio.gestion.ciudades.entity.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {

    @Query(value = "SELECT * FROM gestion_rutas.ciudad WHERE nombre LIKE %?1%", nativeQuery = true)
    List<CiudadEntity> findByNameLike(String nombre);

    boolean existsById(Long id);

}
