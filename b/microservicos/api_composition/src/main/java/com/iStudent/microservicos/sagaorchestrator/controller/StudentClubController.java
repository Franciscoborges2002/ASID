package com.iStudent.microservicos.sagaorchestrator.controller;

import com.iStudent.microservicos.sagaorchestrator.dto.RequestCreateStudentDTO;
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
    public ResponseEntity<ClubStudentDTO> createStudent(@Valid @RequestBody RequestCreateStudentDTO requestCreateStudentDTO) {
        // Process the association logic here
        // For demonstration, we'll just return the request back as a response
        //return ResponseEntity.ok(request);

        ClubStudentDTO response = null;
                sudentClubService.createStudent(requestCreateStudentDTO);

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
}
