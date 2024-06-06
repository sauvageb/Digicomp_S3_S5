package com.azqore.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // Indique à Spring que cette classe doit etre scannee (=car classe Configuration utile)
public class ClientConfiguration {

    // Nous configuration ci-dessous une instance injectable de la classe RestTemplate
    // @Component se positionne en haut d'une classe
    // @Bean se positione au dessus d'une méthode
    // @Bean ou @Component fournissent tous les deux Singletons
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
