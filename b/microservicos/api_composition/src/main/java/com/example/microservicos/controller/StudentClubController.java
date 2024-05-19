package com.example.microservicos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StudentClubController {

    @PostMapping("/student/{studentId}/club/{clubId}")
    public ResponseEntity<Object> associateStudentClub(@PathVariable("id") Long studentId, @PathVariable("id") Long clubId) {
        // Process the association logic here
        // For demonstration, we'll just return the request back as a response
        //return ResponseEntity.ok(request);
    }
}
