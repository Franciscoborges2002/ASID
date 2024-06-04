package com.iStudent.microservicos.sagaorchestrator.extern;

import com.iStudent.microservicos.sagaorchestrator.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentCallerService {

    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    public StudentDTO createStudent(StudentDTO studentDTO) {
        String serviceName = "STUDENTSERVICE";
        String apiStartEndpoint = "/api/v1/";

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);

        if(serviceInstances.isEmpty()){
            System.out.println("Student service is not available right now!");
            throw new IllegalStateException("Student service is not available right now!");
        }

        //serviceInstances.get(0).getUri() gets the uri for the service
        String serviceUrl = serviceInstances.get(0).getUri() + apiStartEndpoint + "students";

        try {
            ResponseEntity<StudentDTO> responseEntity = restTemplate.postForEntity(serviceUrl, studentDTO, StudentDTO.class);

            return responseEntity.getBody(); // Return the StudentDTO object received in the response
        } catch (Exception e) {
            System.out.println("Failed to create student: " + e.getMessage());
            throw new RuntimeException("Failed to create student", e);
        }
    }
}
