package com.example.microservicos.extern;

import com.example.microservicos.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
public class StudentCallerController {

    private RestTemplate restTemplate;

    //Aceitar isto como parametro
    private final String url = "http://localhost:8081";

    private final String endpoint = "/api/v1/";

    public Student findStudentById(Long studentId) throws JsonProcessingException {
        String serviceUrl = url + endpoint + "students/" + studentId; // Replace with your service name and endpoint
        String studentResponse = restTemplate.getForObject(serviceUrl, String.class);

        System.out.println("RESPOSTAAAAAAAAAAAA " + studentResponse);

        //Pass string to object
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(studentResponse, Student.class);



        return student;
    }
}
