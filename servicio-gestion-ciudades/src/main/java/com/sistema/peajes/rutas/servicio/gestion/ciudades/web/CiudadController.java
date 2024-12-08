package com.sistema.peajes.rutas.servicio.gestion.ciudades.web;

import com.sistema.peajes.rutas.servicio.gestion.ciudades.entity.CiudadEntity;
import com.sistema.peajes.rutas.servicio.gestion.ciudades.service.CiudadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * The type Ciudad controller.
 */
@RestController
@RequestMapping("/api/v1/ciudades")
@AllArgsConstructor
@Tag(name = "Ciudades", description = "Gestión de ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    /**
     * Crear ciudad response entity.
     *
     * @param ciudad the ciudad
     * @return the response entity
     */
    @PostMapping
    @Operation(summary = "Crear una nueva ciudad", description = "Permite agregar una nueva ciudad al sistema")
    public ResponseEntity<CiudadEntity> crearCiudad(@RequestBody @Valid CiudadEntity ciudad) {
        CiudadEntity nuevaCiudad = ciudadService.crearCiudad(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCiudad);
    }

    /**
     * Obtener ciudades response entity.
     *
     * @return the response entity
     */
    @GetMapping
    @Operation(summary = "Obtener todas las ciudades", description = "Devuelve una lista de todas las ciudades registradas")
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
    @Operation(summary = "Buscar una ciudad por id", description = "Devuelve la ciudad asociada al id")
    public ResponseEntity<CiudadEntity> obtenerCiudadPorId(@PathVariable @NotNull Long ciudadId) {
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
    @Operation(summary = "Buscar ciudades por nombre", description = "Busca ciudades cuyo nombre contenga una cadena específica")
    public List<CiudadEntity> buscarCiudadPorNombre(@RequestParam String nombre) {
        return ciudadService.buscarCiudadPorNombre(nombre);
    }

    /**
     * Actualizar ciudad response entity.
     *
     * @param id          the id
     * @param nuevaCiudad the nueva ciudad
     * @return the response entity
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una ciudad", description = "Actualiza los datos de una ciudad existente")
    public ResponseEntity<CiudadEntity> actualizarCiudad(@PathVariable Long id, @RequestBody CiudadEntity nuevaCiudad) {
        CiudadEntity ciudadActualizada = ciudadService.actualizarCiudad(id, nuevaCiudad);
        return ResponseEntity.ok(ciudadActualizada);
    }

    /**
     * Eliminar ciudad response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una ciudad", description = "Elimina una ciudad específica por su ID")
    public ResponseEntity<Void> eliminarCiudad(@PathVariable Long id) {
        ciudadService.eliminarCiudad(id);
        return ResponseEntity.noContent().build();
    }

}
