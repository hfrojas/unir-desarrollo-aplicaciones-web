package com.sistema.peajes.rutas.servicio.gestion.rutas.web;

import com.sistema.peajes.rutas.servicio.gestion.rutas.entity.RutaEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.peajes.rutas.servicio.gestion.rutas.domain.Ruta;
import com.sistema.peajes.rutas.servicio.gestion.rutas.service.RutaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rutas")
@Tag(name = "Rutas", description = "Operaciones relacionadas con las rutas")
@AllArgsConstructor
public class RutaController {

    private RutaService rutaService;

    @PostMapping
    @Operation(summary = "Crear una nueva ruta", description = "Agrega una nueva ruta a la base de datos")
    public ResponseEntity<Ruta> crearRuta(@RequestBody @Valid Ruta ruta) {
        Ruta nuevaRuta = rutaService.crearRuta(ruta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRuta);
    }

    @GetMapping
    @Operation(summary = "Listar todas las rutas", description = "Obtiene todas las rutas registradas en la base de datos")
    public ResponseEntity<List<Ruta>> obtenerRutas() {
        List<Ruta> rutas = rutaService.obtenerRutas();
        return ResponseEntity.ok(rutas);
    }

    @Operation(summary = "Buscar rutas por ciudad de origen", description = "Permite buscar rutas que tengan una ciudad de origen específica.")
    @GetMapping("/buscar-origen")
    @ResponseStatus(HttpStatus.OK)
    public List<RutaEntity> obtenerRutasPorOrigen(@RequestParam Long ciudadId) {
        return rutaService.obtenerRutasPorOrigen(ciudadId);
    }

}
