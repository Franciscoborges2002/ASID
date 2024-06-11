package com.iStudent.microservicos.sagaorchestrator.extern;

import com.iStudent.microservicos.sagaorchestrator.dto.ReturnClubTownDTO;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class ClubCallerService {
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    public ReturnClubTownDTO associateClubTown(Long clubId, Long townId) {
        String serviceName = "CLUBSERVICE";
        String apiStartEndpoint = "/api/v1/";

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if(serviceInstances.isEmpty()){
            System.out.println("Parent service is not available right now!");
            throw new IllegalStateException("Parent service is not available right now!");
        }

        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "clubs/" + clubId + "/town/" + townId;

        try {
            ResponseEntity<ReturnClubTownDTO> responseEntity = restTemplate.postForEntity(serviceUrl, null, ReturnClubTownDTO.class);
            return responseEntity.getBody(); // Return the StudentDTO object received in the response
        } catch (Exception e) {
            System.out.println("Failed to create parent: " + e.getMessage());
            throw new RuntimeException("Failed to create parent", e);
        }
    }

    public ReturnClubTownDTO desassociateClubTown(Long clubId, Long townId) {
        String serviceName = "CLUBSERVICE";
        String apiStartEndpoint = "/api/v1/";

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if(serviceInstances.isEmpty()){
            System.out.println("Parent service is not available right now!");
            throw new IllegalStateException("Parent service is not available right now!");
        }

        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "clubs/" + clubId + "/town/" + townId + "/remove";

        try {
            ResponseEntity<ReturnClubTownDTO> responseEntity = restTemplate.postForEntity(serviceUrl, null, ReturnClubTownDTO.class);
            return responseEntity.getBody(); // Return the StudentDTO object received in the response
        } catch (Exception e) {
            System.out.println("Failed to create parent: " + e.getMessage());
            throw new RuntimeException("Failed to create parent", e);
        }
    }
}
