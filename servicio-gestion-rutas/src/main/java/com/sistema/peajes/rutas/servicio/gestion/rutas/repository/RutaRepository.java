package com.sistema.peajes.rutas.servicio.gestion.rutas.repository;

import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.RutaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<RutaEntity, Long> {

    @Query(value = "SELECT * FROM ruta WHERE ciudad_id_origen = ?1", nativeQuery = true)
    List<RutaEntity> findByCiudadOrigenId(Long ciudadId);

    @Query(value = "SELECT * FROM ruta WHERE ciudad_id_destino = ?1", nativeQuery = true)
    List<RutaEntity> findByCiudadDestinoId(Long ciudadId);

    @Query(value = "SELECT * FROM ruta WHERE nombre LIKE %:nombre%", nativeQuery = true)
    List<RutaEntity> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT MAX(r.id) FROM RutaEntity r")
    Long findMaxId();

}