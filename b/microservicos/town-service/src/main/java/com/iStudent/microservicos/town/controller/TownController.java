package com.iStudent.microservicos.town.controller;

import com.iStudent.microservicos.town.dto.TownDTO;
import com.iStudent.microservicos.town.model.Town;
import com.iStudent.microservicos.town.service.TownService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/towns")
@AllArgsConstructor
public class TownController {

    private final TownService townService;

    @GetMapping("/all")
    public ResponseEntity<List<TownDTO>> getTowns(){
        List<TownDTO> towns = townService.getTowns();
        return ResponseEntity.ok(towns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TownDTO> getTownByid(@PathVariable("id") Long townId){
        Optional<TownDTO> town = this.townService.findByTownId(townId);

        //Return the towndto if in the optional is something
        return town.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    //Needs to be like /town?name={name} use %20 for spaces
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<TownDTO> getTownByName(@RequestParam(value="name") String townName){

        //When name has spaces, remove asci for space
        if(townName.contains("%20")){
            townName = townName.replace("%20", " ");
        }

        System.out.println(townName);

        Optional<TownDTO> town = this.townService.findByTownName(townName);

        //Return the towndto if in the optional is something
        return town.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }
}
