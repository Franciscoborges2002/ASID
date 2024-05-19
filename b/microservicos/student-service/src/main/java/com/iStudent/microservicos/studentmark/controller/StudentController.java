package com.iStudent.microservicos.studentmark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iStudent.microservicos.studentmark.dto.MarkDTO;
import com.iStudent.microservicos.studentmark.dto.StudentDTO;
import com.iStudent.microservicos.studentmark.model.Student;
import com.iStudent.microservicos.studentmark.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() throws Exception {
        return ResponseEntity
                .ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long studentId) {
        Optional<StudentDTO> student = this.studentService.getStudentById(studentId);

        if (student.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            return ResponseEntity
                    .ok(student.get());

        }
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO,
                                                 UriComponentsBuilder uriComponentsBuilder) throws JsonProcessingException {
        long newStudentId = studentService.addStudent(studentDTO);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/students/{id}")
                        .build(newStudentId))
                .build();
    }

    @PostMapping("/{id}/add/mark")
    public ResponseEntity<StudentDTO> addMarkToStudent(@PathVariable("id") Long studentId,
                                                       @Valid @RequestBody MarkDTO markDTO) {

        if (studentService.addMarkToStudent(studentId, markDTO)) {
            return ResponseEntity.
                    ok().
                    build();
        } else {
            return ResponseEntity.
                    notFound().
                    build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDTO> deleteStudentById(@PathVariable("id") Long studentId) {
        this.studentService.deleteStudentById(studentId);

        return ResponseEntity
                .noContent()
                .build();
    }
}

