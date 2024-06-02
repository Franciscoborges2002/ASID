package com.iStudent.microservicos.studentmark.extern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iStudent.microservicos.studentmark.dto.ClubDTO;
import com.iStudent.microservicos.studentmark.model.Club;
import com.iStudent.microservicos.studentmark.model.Town;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
public class ClubCallerController {

    private RestTemplate restTemplate;

    //Aceitar isto como parametro
    private final String url = "http://localhost:8081";

    private final String endpoint = "/api/v1/";

    public ClubDTO findClubById(Long clubId) throws JsonProcessingException {
        String serviceUrl = url + endpoint + "clubs/" + clubId; // Replace with your service name and endpoint
        String clubResponse = restTemplate.getForObject(serviceUrl, String.class);

        System.out.println("RESPOSTAAAAAAAAAAAA " + clubResponse);

        //Pass it to an Club
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(clubResponse, ClubDTO.class);
    }
}
