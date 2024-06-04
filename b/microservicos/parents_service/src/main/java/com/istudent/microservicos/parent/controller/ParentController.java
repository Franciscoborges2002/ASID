package com.istudent.microservicos.parent.controller;

import com.istudent.microservicos.parent.dto.DeleteDTO;
import com.istudent.microservicos.parent.dto.ParentDTO;
import com.istudent.microservicos.parent.service.ParentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parents")
@AllArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<List<ParentDTO>> getAllParents() {
        try {
            List<ParentDTO> parents = parentService.getAllParents();

            return ResponseEntity.ok(parents);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching parents ", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDTO> getParentById(@PathVariable("id") Long parentId) {
        Optional<ParentDTO> parent = this.parentService.getParentById(parentId);

        return parent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    @PostMapping
    public ResponseEntity<ParentDTO> addParent(@Valid @RequestBody ParentDTO parentDTO) {
        ParentDTO parentReturn = parentService.addParent(parentDTO);

        return ResponseEntity
                .ok(parentReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDTO> deleteStudentById(@PathVariable("id") Long parentId) {
        return ResponseEntity.ok(this.parentService.deleteParentById(parentId));
    }
}
