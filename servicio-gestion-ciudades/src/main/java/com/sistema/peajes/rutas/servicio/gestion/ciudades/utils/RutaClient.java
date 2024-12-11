package com.sistema.peajes.rutas.servicio.gestion.ciudades.utils;

import com.sistema.peajes.rutas.servicio.gestion.ciudades.dto.Ruta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class RutaClient {

    private final WebClient webClient;

    /**
     * Constructor de RutaClient.
     *
     * @param webClientBuilder el constructor de WebClient
     * @param rutasServiceUrl  la URL base del servicio de rutas
     */
    public RutaClient(WebClient.Builder webClientBuilder, @Value("${microservicio.rutas.url}") String rutasServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(rutasServiceUrl).build();
    }

    /**
     * Busca las rutas cuyo origen coincide con una ciudad específica.
     *
     * @param ciudadOrigenId el ID de la ciudad de origen
     * @return un Mono que contiene la lista de nombres de rutas encontradas
     */
    public Mono<List<Ruta>> buscarRutasPorOrigen(Long ciudadOrigenId) {
        return webClient.get()
                .uri("/api/v1/rutas/buscar-origen?ciudadId={ciudadOrigenId}", ciudadOrigenId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Ruta>>() {});
    }

    /**
     * Busca las rutas cuyo destino coincide con una ciudad específica.
     *
     * @param ciudadDestinoId el ID de la ciudad de destino
     * @return un Mono que contiene la lista de nombres de rutas encontradas
     */
    public Mono<List<Ruta>> buscarRutasPorDestino(Long ciudadDestinoId) {
        return webClient.get()
                .uri("/api/v1/rutas/buscar-destino?ciudadId={ciudadDestinoId}", ciudadDestinoId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Ruta>>() {});
    }

}
