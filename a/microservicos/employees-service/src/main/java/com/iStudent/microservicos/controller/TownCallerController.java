package com.iStudent.microservicos.controller;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Applications;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
public class TownCallerController {

    //private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;

    public String findTownById(Long townId) {
        String serviceUrl = "http://localhost:8081/api/v1/town/" + townId; // Replace with your service name and endpoint
        String response = restTemplate.getForObject(serviceUrl, String.class);

        //System.out.println("RESPOSTAAAAAAAAAAAA " + response);
        return response;
    }

    public String findTownByName(String townName) {

        //If town contains spaces, need to replace to
        if(townName.contains(" ")){
            townName = townName.replace(" ", "%20");
        }

        String serviceUrl = "http://localhost:8081/api/v1/town?name=" + townName; // Replace with your service name and endpoint
        String response = restTemplate.getForObject(serviceUrl, String.class);

        //System.out.println("RESPOSTAAAAAAAAAAAA " + response);
        return response;
    }
}
