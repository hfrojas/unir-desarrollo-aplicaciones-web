package com.sistema.peajes.rutas.servicio.gestion.rutas.web;

import com.sistema.peajes.rutas.servicio.gestion.rutas.domain.Ciudad;
import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.CiudadEntity;
import com.sistema.peajes.rutas.servicio.gestion.rutas.service.CiudadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ciudades")
@AllArgsConstructor
@Tag(name = "Ciudades", description = "Operaciones relacionadas con las ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    @PostMapping
    @Operation(summary = "Crear una nueva ciudad", description = "Agrega una nueva ciudad a la base de datos")
    public ResponseEntity<Ciudad> crearCiudad(@RequestBody @Valid Ciudad ciudad) {
        Ciudad nuevaCiudad = ciudadService.crearCiudad(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCiudad);
    }

    @GetMapping
    @Operation(summary = "Listar todas las ciudades", description = "Obtiene todas las ciudades registradas en la base de datos")
    public ResponseEntity<List<Ciudad>> obtenerCiudades() {
        List<Ciudad> ciudades = ciudadService.obtenerCiudades();
        return ResponseEntity.ok(ciudades);
    }

    @GetMapping("/{ciudadId}")
    @Operation(summary = "Buscar ciudad por ID", description = "Obtiene los detalles de una ciudad por su ID")
    public ResponseEntity<Ciudad> obtenerCiudadPorId(@PathVariable @NotNull Long ciudadId) {
        return ciudadService.obtenerCiudadPorId(ciudadId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @Operation(summary = "Buscar ciudades por nombre", description = "Permite buscar ciudades que contengan el nombre proporcionado.")
    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public List<CiudadEntity> buscarCiudadPorNombre(@RequestParam String nombre) {
        return ciudadService.buscarCiudadPorNombre(nombre);
    }

}
