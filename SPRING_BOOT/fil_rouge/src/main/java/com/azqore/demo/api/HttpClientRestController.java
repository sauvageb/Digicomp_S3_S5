package com.azqore.demo.api;

import com.azqore.demo.api.dto.RandomUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HttpClientRestController {

    private final RestTemplate restTemplate;

    @GetMapping( "/test")
    public ResponseEntity<?> test(){
        // Appel réseau vers un service tier
        String urlAPI = "https://randomuser.me/api?results=5";
        RandomUserResponse response  = restTemplate.getForObject(urlAPI, RandomUserResponse.class);

        // Retourne le resultat recu
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}
