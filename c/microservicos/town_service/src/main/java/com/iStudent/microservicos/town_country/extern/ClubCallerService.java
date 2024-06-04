package com.iStudent.microservicos.town_country.extern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iStudent.microservicos.town_country.dto.ClubDTO;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class ClubCallerService {

    private DiscoveryClient discoveryClient;

    private final String serviceName = "CLUBSERVICE";
    private final String apiStartEndpoint = "/api/v1/";

    private RestTemplate restTemplate;

    public ClubDTO findClubById(Long clubId) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if(serviceInstances.isEmpty()){
            System.out.println("Club service is not available right now");
            throw new IllegalStateException("Club service is not available right now");
        }
        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "clubs/" + clubId; // Replace with your service name and endpoint

        return restTemplate.getForObject(serviceUrl, ClubDTO.class);
    }
}
