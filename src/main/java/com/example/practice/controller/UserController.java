package com.example.practice.controller;


import com.example.practice.dto.AddUserDTO;
import com.example.practice.dto.RegistrationDTO;
import com.example.practice.dto.TeamDTO;
import com.example.practice.dto.UserDTO;
import com.example.practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    ResponseEntity<List<UserDTO>> getAllUser(){
        return new ResponseEntity<>( userService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<UserDTO> createUser(@Valid @RequestBody AddUserDTO addUserDTO){
        return new ResponseEntity<>( userService.createUser(addUserDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>( userService.deleteUSer(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@Valid @RequestBody AddUserDTO addUserDTO){
        return new ResponseEntity<>( userService.updateUser(id,addUserDTO), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    ResponseEntity<UserDTO> updatePartialUser(@PathVariable Long id, @Valid @RequestBody Map<String, Object> changes){
        return new ResponseEntity<>( userService.updatePartialUser(id,changes), HttpStatus.CREATED);
    }



    @GetMapping("/{id}/team")
    ResponseEntity<List<TeamDTO>>getTeamsOfAnUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getTeamsOfAnUser(id),HttpStatus.OK);
    }



}
