package com.example.microservicos.controller;

import com.example.microservicos.dto.ClubDTO;
import com.example.microservicos.dto.ReturnClubTownDTO;
import com.example.microservicos.dto.ClubStudentDTO;
import com.example.microservicos.model.Club;
import com.example.microservicos.service.ClubService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clubs")
@AllArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<ClubDTO>> getAllClubs() {
        return ResponseEntity.
                ok(clubService.getAllClubs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable("id") Long clubId) {
        Optional<ClubDTO> club = this.clubService.getClubById(clubId);

        if (club.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            return ResponseEntity
                    .ok(club.get());

        }
    }

    @PostMapping
    public ResponseEntity<Club> addClub(@Valid @RequestBody ClubDTO clubDTO) {

        Club createdClub = clubService.addClub(clubDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdClub);
    }

    @PostMapping("/{clubId}/student/{studentId}")
    public ResponseEntity<ClubStudentDTO> addClub(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId) throws JsonProcessingException {

        //Send to the service
        ClubStudentDTO response = clubService.attachStudentToClub(clubId, studentId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{clubId}/town/{townId}")
    public ResponseEntity<ReturnClubTownDTO> attachClubTown(@PathVariable("clubId") Long clubId, @PathVariable("townId") Long townId) {

        //Send to the service
        ReturnClubTownDTO response = clubService.attachTowntoClub(clubId, townId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{clubId}/town/{townId}/remove")
    public ResponseEntity<ReturnClubTownDTO> dettachClubTown(@PathVariable("clubId") Long clubId, @PathVariable("townId") Long townId) {

        //Send to the service
        ReturnClubTownDTO response = clubService.dettachTowntoClub(clubId, townId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
