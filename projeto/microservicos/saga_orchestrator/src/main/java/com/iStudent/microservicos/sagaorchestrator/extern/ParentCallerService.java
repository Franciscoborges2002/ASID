package com.iStudent.microservicos.sagaorchestrator.extern;

import com.iStudent.microservicos.sagaorchestrator.dto.ParentDTO;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class ParentCallerService {

    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    public ParentDTO createParent(ParentDTO parentDTO) {
        String serviceName = "PARENTSERVICE";
        String apiStartEndpoint = "/api/v1/";

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if(serviceInstances.isEmpty()){
            System.out.println("Parent service is not available right now!");
            throw new IllegalStateException("Parent service is not available right now!");
        }

        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "parents";
        try {
            ResponseEntity<ParentDTO> responseEntity = restTemplate.postForEntity(serviceUrl, parentDTO, ParentDTO.class);
            return responseEntity.getBody(); // Return the StudentDTO object received in the response
        } catch (Exception e) {
            System.out.println("Failed to create parent: " + e.getMessage());
            throw new RuntimeException("Failed to create parent", e);
        }
    }

    public String deleteParent(ParentDTO parentDTO) {
        String serviceName = "PARENTSERVICE";
        String apiStartEndpoint = "/api/v1/";

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if(serviceInstances.isEmpty()){
            System.out.println("Parent service is not available right now!");
            throw new IllegalStateException("Parent service is not available right now!");
        }

        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "parents/" + parentDTO.getId(); // Replace with your service name and endpoint

        try {
            restTemplate.delete(serviceUrl);
            return "Parent was deleted"; // Return student
        } catch (Exception e) {
            System.out.println("Failed to create parent: " + e.getMessage());
            throw new RuntimeException("Failed to create parent", e);
        }
    }
}
