package com.example.clientappdemo.controller;

import com.example.clientappdemo.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("")
    public ResponseEntity<List<Driver>> getAll(@RegisteredOAuth2AuthorizedClient("drivers-client") OAuth2AuthorizedClient authorizedClient) {
        // use @AuthenticationPrincipal Jwt jwt as a method parameter here or in the resource server
        String jwtAccessToken = authorizedClient.getAccessToken().getTokenValue();
        String url = "http://127.0.0.1:8082/api/drivers";  // endpoint of the Resource server to get the resource

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // the response entity type must matches the return type of the exchange method
        ResponseEntity<List<Driver>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        List<Driver> drivers = response.getBody();

        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

}
