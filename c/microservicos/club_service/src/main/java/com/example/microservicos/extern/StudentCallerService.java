package com.example.microservicos.extern;

import com.example.microservicos.dto.StudentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentCallerService {

    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    private final String serviceName = "STUDENTSERVICE";
    private final String apiStartEndpoint = "/api/v1/";

    public StudentDTO findStudentById(Long studentId) throws JsonProcessingException {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if (serviceInstances.isEmpty()) {
            System.out.println("Service is not available right now");
            throw new IllegalStateException("Service is not available right now");
        }
        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "students/" + studentId; // Replace with your service name and endpoint

        StudentDTO studentResponse = restTemplate.getForObject(serviceUrl, StudentDTO.class);

        return studentResponse;
    }
}
