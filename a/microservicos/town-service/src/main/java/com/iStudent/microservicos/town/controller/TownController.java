package com.iStudent.microservicos.town.controller;

import com.iStudent.microservicos.town.dto.TownDTO;
import com.iStudent.microservicos.town.service.TownService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/town")
@AllArgsConstructor
public class TownController {

    private final TownService townService;

    @GetMapping("/{id}")
    public ResponseEntity<TownDTO> getTownByid(@PathVariable("id") Long townId){
        Optional<TownDTO> town = this.townService.findByTownId(townId);

        //Return the towndto if in the optional is something
        return town.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    //Nees to be like /town?name={name}
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<TownDTO> getTownByid(@RequestParam(value="name") String townName){
        Optional<TownDTO> town = this.townService.findByTownName(townName);

        //Return the towndto if in the optional is something
        return town.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }
}
