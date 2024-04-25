package com.iStudent.microservicos.teachers.controller;

import com.iStudent.microservicos.teachers.dto.TeacherDTO;
import com.iStudent.microservicos.teachers.service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity
                .ok(teacherService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") Long teacherId) {
        Optional<TeacherDTO> teacher = this.teacherService.getTeacherById(teacherId);

        if (teacher.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            return ResponseEntity
                    .ok(teacher.get());

        }
    }


    @PostMapping
    public ResponseEntity<TeacherDTO> addTeacher(@Valid @RequestBody TeacherDTO teacherDTO,
                                                 UriComponentsBuilder uriComponentsBuilder) {

        long teacherId = teacherService.addTeacher(teacherDTO);

        return ResponseEntity.
                created(uriComponentsBuilder.path("/teachers/{id}").
                        build(teacherId)).build();

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TeacherDTO> changeSubject(@Valid @RequestBody TeacherDTO teacherDTO,
                                                    @PathVariable("id") Long id) {

        TeacherDTO teacher = this.teacherService.changeTeacherSubject(id, teacherDTO);

        if (teacher == null) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            return ResponseEntity
                    .ok(teacher);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDTO> deleteTeacherById(@PathVariable("id") Long teacherId) {
        this.teacherService.deleteTeacherById(teacherId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
