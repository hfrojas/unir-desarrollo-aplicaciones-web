package com.sistema.peajes.rutas.servicio.gestion.rutas.service;

import com.sistema.peajes.rutas.servicio.gestion.rutas.domain.Ciudad;
import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.CiudadEntity;
import com.sistema.peajes.rutas.servicio.gestion.rutas.repository.CiudadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public Ciudad crearCiudad(Ciudad ciudad) {
        CiudadEntity ciudadEntity = new CiudadEntity();
        ciudadEntity.setNombre(ciudad.getNombre());

        CiudadEntity nuevaCiudad = ciudadRepository.save(ciudadEntity);
        return new Ciudad(nuevaCiudad.getId(), nuevaCiudad.getNombre());
    }

    public List<Ciudad> obtenerCiudades() {
        List<CiudadEntity> ciudades = ciudadRepository.findAll();
        return ciudades.stream().map(ciudad -> new Ciudad(ciudad.getId(), ciudad.getNombre()))
                .collect(Collectors.toList());
    }

    public Optional<Ciudad> obtenerCiudadPorId(Long ciudadId) {
        return ciudadRepository.findById(ciudadId)
                .map(ciudadEntity -> new Ciudad(ciudadEntity.getId(), ciudadEntity.getNombre()));
    }

    public List<CiudadEntity> buscarCiudadPorNombre(String nombre) {
        return ciudadRepository.findByNameLike(nombre);
    }
}
