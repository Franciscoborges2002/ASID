package com.iStudent.microservicos.student.controller;

import com.iStudent.microservicos.student.dto.StudentDTO;
import com.iStudent.microservicos.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() throws Exception {

        if(true){
            throw new Exception();
        }

        return ResponseEntity
                .ok(studentService.getAllStudents());
    }
}
