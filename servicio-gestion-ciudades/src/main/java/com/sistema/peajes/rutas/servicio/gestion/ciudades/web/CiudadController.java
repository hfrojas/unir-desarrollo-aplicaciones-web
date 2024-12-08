package com.sistema.peajes.rutas.servicio.gestion.ciudades.web;

import com.sistema.peajes.rutas.servicio.gestion.ciudades.entity.CiudadEntity;
import com.sistema.peajes.rutas.servicio.gestion.ciudades.service.CiudadService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The type Ciudad controller.
 */
@RestController
@RequestMapping("/api/v1/ciudades")
@AllArgsConstructor
public class CiudadController {

    private final CiudadService ciudadService;

    @PostMapping
    @Operation(
            summary = "Crear una nueva ciudad",
            description = "Permite agregar una nueva ciudad al sistema",
            operationId = "crearCiudad",
            tags = {"Administración de Ciudades"}
    )
    public ResponseEntity<CiudadEntity> crearCiudad(@RequestParam String nombre) {

        if (StringUtils.isBlank(nombre)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la ciudad es obligatorio y no puede estar vacío.");
        }
        if (!StringUtils.isAlphaSpace(nombre)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la ciudad debe contener solo caracteres alfabéticos.");
        }

        CiudadEntity ciudad = new CiudadEntity();
        ciudad.setNombre(nombre);

        CiudadEntity nuevaCiudad = ciudadService.crearCiudad(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCiudad);
    }

    /**
     * Obtener ciudades response entity.
     *
     * @return the response entity
     */
    @GetMapping
    @Operation(
            summary = "Obtener todas las ciudades",
            description = "Devuelve una lista de todas las ciudades registradas",
            operationId = "obtenerCiudades",
            tags = {"Consulta de Ciudades"} // Tag específico para este método
    )
    public ResponseEntity<List<CiudadEntity>> obtenerCiudades() {
        List<CiudadEntity> ciudades = ciudadService.obtenerCiudades();
        return ResponseEntity.ok(ciudades);
    }

    /**
     * Obtener ciudad por id response entity.
     *
     * @param ciudadId the ciudad id
     * @return the response entity
     */
    @GetMapping("/{ciudadId}")
    @Operation(
            summary = "Buscar una ciudad por id",
            description = "Devuelve la ciudad asociada al id",
            operationId = "obtenerCiudadPorId",
            tags = {"Consulta de Ciudades"} // Tag específico para este método
    )
    public ResponseEntity<CiudadEntity> obtenerCiudadPorId(@PathVariable Long ciudadId) {

        if (ciudadId == null || ciudadId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la ciudad debe ser informado y debe ser mayor que cero.");
        }

        return ciudadService.obtenerCiudadPorId(ciudadId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    /**
     * Buscar ciudad por nombre list.
     *
     * @param nombre the nombre
     * @return the list
     */
    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Buscar ciudades por nombre",
            description = "Busca ciudades cuyo nombre contenga una cadena específica",
            operationId = "buscarCiudadPorNombre",
            tags = {"Consulta de Ciudades"} // Tag específico para este método
    )
    public List<CiudadEntity> buscarCiudadPorNombre(@RequestParam String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la ciudad es obligatorio y no puede estar vacío.");
        }

        return ciudadService.buscarCiudadPorNombre(nombre);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una ciudad",
            description = "Actualiza el nombre de una ciudad existente",
            operationId = "actualizarCiudad",
            tags = {"Administración de Ciudades"} // Tag específico para este método
    )
    public ResponseEntity<CiudadEntity> actualizarCiudad(@PathVariable Long id, @RequestParam String nombre) {

        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la ciudad es obligatorio y debe ser un número positivo.");
        }

        if (StringUtils.isBlank(nombre)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la ciudad es obligatorio y no puede estar vacío.");
        }
        if (!StringUtils.isAlphaSpace(nombre)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la ciudad debe contener solo caracteres alfabéticos.");
        }

        CiudadEntity ciudadActualizada = ciudadService.actualizarCiudad(id, nombre);
        return ResponseEntity.ok(ciudadActualizada);
    }

    /**
     * Eliminar ciudad response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una ciudad",
            description = "Elimina una ciudad específica por su ID",
            operationId = "eliminarCiudad",
            tags = {"Administración de Ciudades"} // Tag específico para este método
    )
    public ResponseEntity<Void> eliminarCiudad(@PathVariable Long id) {

        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de la ciudad es obligatorio y debe ser un número positivo.");
        }

        ciudadService.eliminarCiudad(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Verifica si una ciudad existe por su ID.
     *
     * @param ciudadId ID de la ciudad a verificar.
     * @return ResponseEntity con el resultado de la verificación.
     */
    @GetMapping("/{ciudadId}/existe")
    @Operation(
            summary = "Verificar si una ciudad existe",
            description = "Devuelve true si una ciudad existe con el ID proporcionado, false de lo contrario.",
            operationId = "existeCiudad",
            tags = {"Consulta de Ciudades"} // Tag específico para este método
    )
    public ResponseEntity<Boolean> existeCiudad(@PathVariable Long ciudadId) {
        if (ciudadId == null) {
            return ResponseEntity.badRequest().body(false);
        }
        boolean existe = ciudadService.existeCiudadPorId(ciudadId);
        return ResponseEntity.ok(existe);
    }

}
