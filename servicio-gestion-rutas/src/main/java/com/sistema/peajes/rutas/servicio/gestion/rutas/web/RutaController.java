package com.sistema.peajes.rutas.servicio.gestion.rutas.web;

import com.sistema.peajes.rutas.servicio.gestion.rutas.dto.Ruta;
import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.RutaEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sistema.peajes.rutas.servicio.gestion.rutas.service.RutaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rutas")
@AllArgsConstructor
@Tag(name = "Rutas", description = "Gestión de rutas")
public class RutaController {

    private RutaService rutaService;

    @PostMapping
    @Operation(summary = "Crear una nueva ruta", description = "Permite agregar una nueva ruta al sistema. Se requiere enviar el nombre, origen y destino.")
    public ResponseEntity<RutaEntity> crearRuta(@RequestBody Ruta ruta) {

        if (ruta.getNombre() == null || ruta.getNombre().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la ruta es obligatorio.");
        }

        if (ruta.getCiudadOrigenId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la ciudad de origen es obligatorio.");
        }

        if (ruta.getCiudadDestinoId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la ciudad de destino es obligatorio.");
        }

        RutaEntity rutaEntity = new RutaEntity();
        rutaEntity.setNombre(ruta.getNombre());
        rutaEntity.setCiudadOrigenId(ruta.getCiudadOrigenId());
        rutaEntity.setCiudadDestinoId(ruta.getCiudadDestinoId());

        RutaEntity nuevaRuta = rutaService.crearRuta(rutaEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRuta);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las rutas", description = "Devuelve una lista de todas las rutas registradas en el sistema.")
    public ResponseEntity<List<RutaEntity>> obtenerRutas() {
        List<RutaEntity> rutas = rutaService.obtenerRutas();
        return ResponseEntity.ok(rutas);
    }

    @GetMapping("/buscar-origen")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar rutas por ciudad de origen", description = "Busca y devuelve una lista de rutas cuyo origen coincida con el ID de la ciudad proporcionado.")
    public List<RutaEntity> obtenerRutasPorOrigen(@RequestParam Long ciudadId) {

        if (ciudadId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la ciudad de origen es obligatorio y no puede ser nulo.");
        }

        return rutaService.obtenerRutasPorOrigen(ciudadId);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar rutas por nombre", description = "Busca rutas cuyo nombre contenga la cadena de caracteres proporcionada. Devuelve una lista de coincidencias.")
    public ResponseEntity<List<RutaEntity>> buscarRutasPorNombre(@RequestParam String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la ruta es obligatorio y no puede estar vacío.");
        }

        List<RutaEntity> rutas = rutaService.buscarRutasPorNombre(nombre);
        if (rutas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rutas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una ruta", description = "Actualiza los datos de una ruta existente. Requiere el ID de la ruta y los nuevos datos.")
    public ResponseEntity<RutaEntity> actualizarRuta(@PathVariable Long id, @RequestBody RutaEntity nuevaRuta) {
        RutaEntity rutaActualizada = rutaService.actualizarRuta(id, nuevaRuta);
        return ResponseEntity.ok(rutaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una ruta", description = "Elimina una ruta específica por su ID.")
    public ResponseEntity<Void> eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar una ruta por ID", description = "Devuelve los detalles de una ruta específica por su ID.")
    public ResponseEntity<RutaEntity> buscarRutaPorId(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la ruta es obligatorio y debe ser un número positivo.");
        }

        RutaEntity ruta = rutaService.buscarRutaPorId(id);
        return ResponseEntity.ok(ruta);
    }

    @Operation(
            summary = "Obtener rutas por ciudad de destino",
            description = "Obtiene todas las rutas cuyo destino sea la ciudad especificada. Incluye los nombres de las ciudades de origen y destino."
    )
    @GetMapping("/buscar-destino")
    @ResponseStatus(HttpStatus.OK)
    public List<RutaEntity> obtenerRutasPorDestino(@RequestParam Long ciudadId) {
        if (ciudadId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la ciudad de destino es obligatorio y no puede ser nulo.");
        }
        return rutaService.obtenerRutasPorDestino(ciudadId);
    }




}
