package com.iStudent.microservicos.sagaorchestrator.controller;

import com.iStudent.microservicos.sagaorchestrator.dto.ClubTownDTO;
import com.iStudent.microservicos.sagaorchestrator.dto.RequestCreateStudentDTO;
import com.iStudent.microservicos.sagaorchestrator.dto.ReturnCreateStudentDTO;
import com.iStudent.microservicos.sagaorchestrator.service.GeneralService;
import com.iStudent.microservicos.sagaorchestrator.dto.ClubStudentDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class StudentClubController {

    private final GeneralService sudentClubService;

    @PostMapping("/student")
    public ResponseEntity<ReturnCreateStudentDTO> createStudent(@Valid @RequestBody RequestCreateStudentDTO requestCreateStudentDTO) {
        // Process the association logic here
        // For demonstration, we'll just return the request back as a response
        //return ResponseEntity.ok(request);

        ReturnCreateStudentDTO response = sudentClubService.createStudentParent(requestCreateStudentDTO);


        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/student/{studentId}/club/{clubId}")
    public ResponseEntity<ClubStudentDTO> associateStudentClub(@PathVariable("studentId") Long studentId, @PathVariable("clubId") Long clubId) {
        // Process the association logic here
        // For demonstration, we'll just return the request back as a response
        //return ResponseEntity.ok(request);

        ClubStudentDTO response = sudentClubService.associateStudentClub(studentId, clubId);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/club/{clubId}/town/{townId}")
    public ResponseEntity<ClubTownDTO> associateClubTown(@PathVariable("clubId") Long clubId, @PathVariable("townId") Long townId) {
        // Process the association logic here
        // For demonstration, we'll just return the request back as a response
        //return ResponseEntity.ok(request);

        System.out.println(clubId + " " + townId);

        ClubTownDTO response = sudentClubService.associateClubTown(clubId, townId);

        return ResponseEntity.ok().body(response);
    }
}
