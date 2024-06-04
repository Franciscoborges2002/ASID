package com.iStudent.microservicos.studentmark.controller;

import com.iStudent.microservicos.studentmark.dto.MarkDTO;
import com.iStudent.microservicos.studentmark.dto.StudentDTO;
import com.iStudent.microservicos.studentmark.service.MarkService;
import com.iStudent.microservicos.studentmark.service.StudentService;
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
    public ResponseEntity<List<MarkDTO>> getAllMarks() throws Exception {
        return ResponseEntity
                .ok(markService.getAllMarks());
    }
}
