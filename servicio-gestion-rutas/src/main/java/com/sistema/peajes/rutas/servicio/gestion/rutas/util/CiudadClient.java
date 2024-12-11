package com.sistema.peajes.rutas.servicio.gestion.rutas.util;

import com.sistema.peajes.rutas.servicio.gestion.rutas.dto.CiudadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * The type Ciudad client.
 */
@Component
public class CiudadClient {

    private final WebClient webClient;

    /**
     * Instantiates a new Ciudad client.
     *
     * @param webClientBuilder the web client builder
     * @param ciudadServiceUrl the ciudad service url
     */
    public CiudadClient(WebClient.Builder webClientBuilder, @Value("${microservicio.ciudades.url}") String ciudadServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(ciudadServiceUrl).build();
    }

    /**
     * Obtener nombre ciudad mono.
     *
     * @param ciudadId the ciudad id
     * @return the mono
     */
    public Mono<String> obtenerNombreCiudad(Long ciudadId) {
        return webClient.get()
                .uri("/api/v1/ciudades/{ciudadId}", ciudadId)
                .retrieve()
                .bodyToMono(CiudadResponse.class)
                .map(CiudadResponse::getNombre);
    }

    /**
     * Consulta si una ciudad existe por su ID.
     *
     * @param ciudadId ID de la ciudad a verificar.
     * @return Mono<Boolean>  indicando si la ciudad existe o no.
     */
    public Mono<Boolean> ciudadExiste(Long ciudadId) {
        return webClient.get()
                .uri("/api/v1/ciudades/{ciudadId}/existe", ciudadId)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

}
