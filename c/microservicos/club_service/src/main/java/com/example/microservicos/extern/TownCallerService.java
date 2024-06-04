package com.example.microservicos.extern;

import com.example.microservicos.dto.TownDTO;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class TownCallerService {

    private DiscoveryClient discoveryClient;

    private final String serviceName = "TOWNSERVICE";
    private final String apiStartEndpoint = "/api/v1/";

    private RestTemplate restTemplate;

    public TownDTO findTownById(Long townId) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if(serviceInstances.isEmpty()){
            System.out.println("Service is not available right now");
            throw new IllegalStateException("Service is not available right now");
        }
        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "towns/" + townId; // Replace with your service name and endpoint

        TownDTO townResponse = restTemplate.getForObject(serviceUrl, TownDTO.class);

        return townResponse;
    }

    public TownDTO findTownByName(String townName) {
        townName = townName.replace(" ", "%20");

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if(serviceInstances.isEmpty()){
            System.out.println("Service is not available right now");
            throw new IllegalStateException("Service is not available right now");
        }
        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "towns?name=" + townName; // Replace with your service name and endpoint

        return restTemplate.getForObject(serviceUrl, TownDTO.class);
    }
}
