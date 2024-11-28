package com.sistema.peajes.rutas.servicio.gestion.rutas.service;

import com.sistema.peajes.rutas.servicio.gestion.rutas.domain.Ruta;
import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.RutaEntity;
import com.sistema.peajes.rutas.servicio.gestion.rutas.repository.RutaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RutaService {

    private RutaRepository rutaRepository;

    public Ruta crearRuta(Ruta ruta) {
        RutaEntity rutaEntity = new RutaEntity();
        rutaEntity.setNombre(ruta.getNombre());
        rutaEntity.setCiudadOrigenId(ruta.getCiudadOrigenId());
        rutaEntity.setCiudadDestinoId(ruta.getCiudadDestinoId());

        RutaEntity nuevaRuta = rutaRepository.save(rutaEntity);
        return new Ruta(nuevaRuta.getId(), nuevaRuta.getNombre(),
                nuevaRuta.getCiudadOrigenId(), nuevaRuta.getCiudadDestinoId());
    }

    public List<Ruta> obtenerRutas() {
        List<RutaEntity> rutas = rutaRepository.findAll();
        return rutas.stream().map(ruta -> new Ruta(
                ruta.getId(), ruta.getNombre(), ruta.getCiudadOrigenId(), ruta.getCiudadDestinoId()
        )).collect(Collectors.toList());
    }

    public List<RutaEntity> obtenerRutasPorOrigen(Long ciudadId) {
        return rutaRepository.findByCiudadOrigenId(ciudadId);
    }

}
