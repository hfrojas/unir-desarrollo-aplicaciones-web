package com.sistema.peajes.rutas.servicio.gestion.rutas.util;

import com.sistema.peajes.rutas.servicio.gestion.rutas.dto.CiudadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CiudadClient {

    private final WebClient webClient;

    public CiudadClient(WebClient.Builder webClientBuilder, @Value("${microservicio.ciudades.url}") String ciudadServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(ciudadServiceUrl).build();
    }

    public Mono<String> obtenerNombreCiudad(Long ciudadId) {
        return webClient.get()
                .uri("/api/v1/ciudades/{ciudadId}", ciudadId)
                .retrieve()
                .bodyToMono(CiudadResponse.class)
                .map(CiudadResponse::getNombre);
    }

}
