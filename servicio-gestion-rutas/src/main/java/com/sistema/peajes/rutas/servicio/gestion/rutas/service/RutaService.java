package com.sistema.peajes.rutas.servicio.gestion.rutas.service;

import com.sistema.peajes.rutas.servicio.gestion.rutas.dto.Ruta;
import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.RutaEntity;
import com.sistema.peajes.rutas.servicio.gestion.rutas.repository.RutaRepository;
import com.sistema.peajes.rutas.servicio.gestion.rutas.util.CiudadClient;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Ruta service.
 */
@Service
@AllArgsConstructor
public class RutaService {

    private RutaRepository rutaRepository;

    private final CiudadClient ciudadClient;

    /**
     * Crear ruta ruta.
     *
     * @param ruta the ruta
     * @return the ruta
     */
    public RutaEntity crearRuta(RutaEntity ruta) {

        boolean ciudadOrigenExiste = ciudadClient.ciudadExiste(ruta.getCiudadOrigenId())
                .onErrorReturn(false)
                .block();

        if (!ciudadOrigenExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La ciudad de origen con ID " + ruta.getCiudadOrigenId() + " no existe.");
        }

        boolean ciudadDestinoExiste = ciudadClient.ciudadExiste(ruta.getCiudadDestinoId())
                .onErrorReturn(false)
                .block();

        if (!ciudadDestinoExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La ciudad de destino con ID " + ruta.getCiudadDestinoId() + " no existe.");
        }

        Long maxId = rutaRepository.findMaxId();
        Long nuevoId = (maxId == null) ? 1L : maxId + 1;
        ruta.setId(nuevoId);


        try {
            RutaEntity rutaGuardada = rutaRepository.save(ruta);

            String ciudadOrigen = ciudadClient.obtenerNombreCiudad(ruta.getCiudadOrigenId())
                    .onErrorReturn("Ciudad Origen Desconocida")
                    .block();

            String ciudadDestino = ciudadClient.obtenerNombreCiudad(ruta.getCiudadDestinoId())
                    .onErrorReturn("Ciudad Destino Desconocida")
                    .block();

            rutaGuardada.setCiudadOrigenNombre(ciudadOrigen);
            rutaGuardada.setCiudadDestinoNombre(ciudadDestino);

            return rutaGuardada;

        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("La ruta ya existe: " + ruta.getNombre());
        }
    }

    /**
     * Obtener rutas list.
     *
     * @return the list
     */
    public List<RutaEntity> obtenerRutas() {
        List<RutaEntity> rutas = rutaRepository.findAll();
        return rutas.stream()
                .peek(ruta -> {
                    String ciudadOrigen = ciudadClient.obtenerNombreCiudad(ruta.getCiudadOrigenId())
                            .onErrorReturn("Ciudad Origen Desconocida")
                            .block();

                    String ciudadDestino = ciudadClient.obtenerNombreCiudad(ruta.getCiudadDestinoId())
                            .onErrorReturn("Ciudad Destino Desconocida")
                            .block();

                    ruta.setCiudadOrigenNombre(ciudadOrigen);
                    ruta.setCiudadDestinoNombre(ciudadDestino);
                })
                .collect(Collectors.toList());
    }

    /**
     * Obtener rutas por origen list.
     *
     * @param ciudadId the ciudad id
     * @return the list
     */
    public List<RutaEntity> obtenerRutasPorOrigen(Long ciudadId) {
        List<RutaEntity> rutas = rutaRepository.findByCiudadOrigenId(ciudadId);

        rutas.forEach(ruta -> {
            String ciudadOrigenNombre = ciudadClient.obtenerNombreCiudad(ruta.getCiudadOrigenId())
                    .onErrorReturn("Ciudad Origen No Encontrada")
                    .block();

            String ciudadDestinoNombre = ciudadClient.obtenerNombreCiudad(ruta.getCiudadDestinoId())
                    .onErrorReturn("Ciudad Destino No Encontrada")
                    .block();

            ruta.setCiudadOrigenNombre(ciudadOrigenNombre);
            ruta.setCiudadDestinoNombre(ciudadDestinoNombre);
        });

        return rutas;
    }

    /**
     * Obtener rutas por destino list.
     *
     * @param ciudadId the ciudad id
     * @return the list
     */
    public List<RutaEntity> obtenerRutasPorDestino(Long ciudadId) {
        List<RutaEntity> rutas = rutaRepository.findByCiudadDestinoId(ciudadId);

        rutas.forEach(ruta -> {
            // Obtener el nombre de la ciudad origen
            String ciudadOrigenNombre = ciudadClient.obtenerNombreCiudad(ruta.getCiudadOrigenId())
                    .onErrorReturn("Ciudad Origen No Encontrada")
                    .block();

            // Obtener el nombre de la ciudad destino
            String ciudadDestinoNombre = ciudadClient.obtenerNombreCiudad(ruta.getCiudadDestinoId())
                    .onErrorReturn("Ciudad Destino No Encontrada")
                    .block();

            ruta.setCiudadOrigenNombre(ciudadOrigenNombre);
            ruta.setCiudadDestinoNombre(ciudadDestinoNombre);
        });

        return rutas;
    }


    /**
     * Buscar rutas por nombre list.
     *
     * @param nombre the nombre
     * @return the list
     */
    public List<RutaEntity> buscarRutasPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la ruta no puede estar vacío");
        }

        List<RutaEntity> rutas = rutaRepository.buscarPorNombre(nombre);

        rutas.forEach(ruta -> {
            // Obtener el nombre de la ciudad origen
            String ciudadOrigenNombre = ciudadClient.obtenerNombreCiudad(ruta.getCiudadOrigenId())
                    .onErrorReturn("Ciudad Origen No Encontrada")
                    .block();

            // Obtener el nombre de la ciudad destino
            String ciudadDestinoNombre = ciudadClient.obtenerNombreCiudad(ruta.getCiudadDestinoId())
                    .onErrorReturn("Ciudad Destino No Encontrada")
                    .block();

            ruta.setCiudadOrigenNombre(ciudadOrigenNombre);
            ruta.setCiudadDestinoNombre(ciudadDestinoNombre);
        });

        return rutas;
    }

    /**
     * Actualizar ruta ruta entity.
     *
     * @param id        the id
     * @param nuevaRuta the nueva ruta
     * @return the ruta entity
     */
    public RutaEntity actualizarRuta(Long id, Ruta nuevaRuta) {

        boolean ciudadOrigenExiste = ciudadClient.ciudadExiste(nuevaRuta.getCiudadOrigenId()).block();

        if (!ciudadOrigenExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La ciudad de origen no existe: " + nuevaRuta.getCiudadOrigenId());
        }

        boolean ciudadDestinoExiste = ciudadClient.ciudadExiste(nuevaRuta.getCiudadDestinoId()).block();

        if (!ciudadDestinoExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La ciudad de destino no existe: " + nuevaRuta.getCiudadDestinoId());
        }

        RutaEntity rutaExistente = rutaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una ruta con el ID: " + id));

        rutaExistente.setNombre(nuevaRuta.getNombre());
        rutaExistente.setCiudadOrigenId(nuevaRuta.getCiudadOrigenId());
        rutaExistente.setCiudadDestinoId(nuevaRuta.getCiudadDestinoId());
        rutaExistente.setDistancia(nuevaRuta.getDistancia());
        rutaRepository.save(rutaExistente);

        String ciudadOrigen = ciudadClient.obtenerNombreCiudad(nuevaRuta.getCiudadOrigenId())
                .onErrorReturn("Ciudad Origen Desconocida")
                .block();

        String ciudadDestino = ciudadClient.obtenerNombreCiudad(nuevaRuta.getCiudadDestinoId())
                .onErrorReturn("Ciudad Destino Desconocida")
                .block();

        rutaExistente.setCiudadOrigenNombre(ciudadOrigen);
        rutaExistente.setCiudadDestinoNombre(ciudadDestino);

        return rutaExistente;
    }

    /**
     * Eliminar ruta.
     *
     * @param id the id
     */
    public void eliminarRuta(Long id) {
        if (!rutaRepository.existsById(id)) {
            throw new IllegalArgumentException("La ruta con ID " + id + " no existe");
        }
        rutaRepository.deleteById(id);
    }

    /**
     * Buscar ruta por id ruta entity.
     *
     * @param id the id
     * @return the ruta entity
     */
    public RutaEntity buscarRutaPorId(Long id) {
        RutaEntity ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("La ruta con ID " + id + " no existe."));

        String ciudadOrigenNombre = ciudadClient.obtenerNombreCiudad(ruta.getCiudadOrigenId())
                .onErrorReturn("Ciudad Origen No Encontrada")
                .block();

        String ciudadDestinoNombre = ciudadClient.obtenerNombreCiudad(ruta.getCiudadDestinoId())
                .onErrorReturn("Ciudad Destino No Encontrada")
                .block();

        ruta.setCiudadOrigenNombre(ciudadOrigenNombre);
        ruta.setCiudadDestinoNombre(ciudadDestinoNombre);

        return ruta;

    }

}
