package com.sistema.peajes.rutas.servicio.gestion.ciudades.service;

import com.sistema.peajes.rutas.servicio.gestion.ciudades.entity.CiudadEntity;
import com.sistema.peajes.rutas.servicio.gestion.ciudades.repository.CiudadRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Ciudad service.
 */
@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    /**
     * Instantiates a new Ciudad service.
     *
     * @param ciudadRepository the ciudad repository
     */
    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    /**
     * Crear ciudad ciudad.
     *
     * @param ciudad the ciudad
     * @return the ciudad
     */
    public CiudadEntity crearCiudad(CiudadEntity ciudad) {

        Long maxId = ciudadRepository.findMaxId();
        Long nuevoId = (maxId != null) ? maxId + 1 : 1L;

        ciudad.setId(nuevoId);

        try {
            CiudadEntity nuevaCiudad = ciudadRepository.save(ciudad);
            return new CiudadEntity(nuevaCiudad.getId(), nuevaCiudad.getNombre());
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("El nombre de la ciudad ya existe: " + ciudad.getNombre());
        }
    }

    /**
     * Obtener ciudades list.
     *
     * @return the list
     */
    public List<CiudadEntity> obtenerCiudades() {
        List<CiudadEntity> ciudades = ciudadRepository.findAll();
        return ciudades.stream()
                .sorted(Comparator.comparing(CiudadEntity::getId))
                .map(ciudad -> new CiudadEntity(ciudad.getId(), ciudad.getNombre()))
                .collect(Collectors.toList());
    }

    /**
     * Obtener ciudad por id optional.
     *
     * @param ciudadId the ciudad id
     * @return the optional
     */
    public Optional<CiudadEntity> obtenerCiudadPorId(Long ciudadId) {
        return ciudadRepository.findById(ciudadId)
                .map(ciudadEntity -> new CiudadEntity(ciudadEntity.getId(), ciudadEntity.getNombre()));
    }

    /**
     * Buscar ciudad por nombre list.
     *
     * @param nombre the nombre
     * @return the list
     */
    public List<CiudadEntity> buscarCiudadPorNombre(String nombre) {
        return ciudadRepository.findByNameLike(nombre);
    }

    /**
     * Actualizar ciudad ciudad entity.
     *
     * @param id          the id
     * @param nuevoNombre the nueva ciudad
     * @return the ciudad entity
     */
    public CiudadEntity actualizarCiudad(Long id, String nuevoNombre) {
        CiudadEntity ciudadExistente = ciudadRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("La ciudad con ID " + id + " no existe."));

        ciudadExistente.setNombre(nuevoNombre);
        return ciudadRepository.save(ciudadExistente);
    }

    /**
     * Eliminar ciudad.
     *
     * @param id the id
     */
    public void eliminarCiudad(Long id) {
        if (!ciudadRepository.existsById(id)) {
            throw new IllegalArgumentException("La ciudad con ID " + id + " no existe");
        }
        ciudadRepository.deleteById(id);
    }

    /**
     * Existe ciudad por id boolean.
     *
     * @param ciudadId the ciudad id
     * @return the boolean
     */
    public boolean existeCiudadPorId(Long ciudadId) {
        return ciudadRepository.existsById(ciudadId);
    }

}
