package com.example.practice.controller;

import com.example.practice.dto.AddRegistrationDTO;
import com.example.practice.dto.AddTeamDTO;
import com.example.practice.dto.RegistrationDTO;
import com.example.practice.dto.TeamDTO;
import com.example.practice.service.RegService;
import com.example.practice.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reg")
@RestController
public class RegistrationController {
    @Autowired
    private RegService regService;

    @GetMapping()
    ResponseEntity<List<RegistrationDTO>> getAllReg(){
        return new ResponseEntity<>( regService.getAllReg(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<RegistrationDTO> getRegById(@PathVariable Long id){
        return new ResponseEntity<>( regService.getRegById(id), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<RegistrationDTO> createReg(@Valid @RequestBody AddRegistrationDTO addRegistrationDTO){
        return new ResponseEntity<>( regService.createReg(addRegistrationDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<RegistrationDTO> deleteReg(@PathVariable Long id){
        return new ResponseEntity<>( regService.deleteReg(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{id}/")
    ResponseEntity<List<RegistrationDTO>> getAllReg(@PathVariable Long id){
        return new ResponseEntity<>( regService.getAllRegOfAnUser(id), HttpStatus.OK);
    }

    @GetMapping("/event/{id}/")
    ResponseEntity<List<RegistrationDTO>> getAllRegOfAnEvent(@PathVariable Long id){
        return new ResponseEntity<>( regService.getAllRegOfAnEvent(id), HttpStatus.OK);
    }

}
