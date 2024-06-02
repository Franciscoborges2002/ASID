package com.istudent.microservicos.parent.controller;

import com.istudent.microservicos.parent.dto.ParentDTO;
import com.istudent.microservicos.parent.extern.TownCallerService;
import com.istudent.microservicos.parent.service.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

            System.out.println(parents.get(0).getAge());
            return ResponseEntity.ok(parents);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching parents", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDTO> getParentById(@PathVariable("id") Long parentId) {
        Optional<ParentDTO> parent = this.parentService.getParentById(parentId);

        return parent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }
}
