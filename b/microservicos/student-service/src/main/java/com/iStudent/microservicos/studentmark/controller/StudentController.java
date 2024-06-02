package com.iStudent.microservicos.studentmark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iStudent.microservicos.studentmark.dto.MarkDTO;
import com.iStudent.microservicos.studentmark.dto.StudentDTO;
import com.iStudent.microservicos.studentmark.dto.ClubStudentDTO;
import com.iStudent.microservicos.studentmark.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping()
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity
                .ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long studentId) {
        Optional<StudentDTO> student = this.studentService.getStudentById(studentId);

        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO,
                                                 UriComponentsBuilder uriComponentsBuilder) throws JsonProcessingException {

        StudentDTO studentReturn = studentService.addStudent(studentDTO);

        return ResponseEntity
                .ok(studentReturn);
    }

    @PostMapping("/{studentId}/club/{clubId}")
    public ResponseEntity<ClubStudentDTO> associateClubAndStudent(@PathVariable("studentId") Long studentId, @PathVariable("clubId") Long clubId) throws JsonProcessingException {
        ClubStudentDTO response = studentService.attachClubToStudent(studentId, clubId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
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

