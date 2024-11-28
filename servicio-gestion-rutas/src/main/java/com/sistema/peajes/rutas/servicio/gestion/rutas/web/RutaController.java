package com.sistema.peajes.rutas.servicio.gestion.rutas.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rutas")
@Tag(name = "Rutas", description = "Operaciones relacionadas con las rutas")
public class RutaController {
}
