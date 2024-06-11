package com.iStudent.microservicos.student.controller;

import com.iStudent.microservicos.student.dto.MarkDTO;
import com.iStudent.microservicos.student.dto.StudentDTO;
import com.iStudent.microservicos.student.service.MarkService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mark")
@AllArgsConstructor
public class MarkController {

    private final MarkService markService;

    @GetMapping
    public ResponseEntity<List<MarkDTO>> getAllStudents() throws Exception {
        return ResponseEntity
                .ok(markService.getAllMarks());
    }
}
