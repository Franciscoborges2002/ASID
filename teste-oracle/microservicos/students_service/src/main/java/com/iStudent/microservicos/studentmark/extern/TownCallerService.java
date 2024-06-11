package com.iStudent.microservicos.studentmark.extern;

import com.iStudent.microservicos.studentmark.dto.TownDTO;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
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

        return restTemplate.getForObject(serviceUrl, TownDTO.class);
    }

    public TownDTO findTownByName(String townName) {
        townName = townName.replace(" ", "%20");

        System.out.println(townName);

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
