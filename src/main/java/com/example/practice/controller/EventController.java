package com.example.practice.controller;

import com.example.practice.dto.*;
import com.example.practice.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/event")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping()
    ResponseEntity<List<EventDTO>> getAllEvent(){
        return new ResponseEntity<>( eventService.getAllEvent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<EventDTO> getEventById(@PathVariable Long id){
        return new ResponseEntity<>( eventService.getEventById(id), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<EventDTO> createEvent(@Valid @RequestBody AddEventDTO addEventDTO){
        return new ResponseEntity<>( eventService.createEvent(addEventDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<EventDTO> deleteReg(@PathVariable Long id){
        return new ResponseEntity<>( eventService.deleteEvent(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<EventDTO> updateUser(@PathVariable Long id, @Valid @RequestBody AddEventDTO addEventDTO){
        return new ResponseEntity<>( eventService.updateEvent(id,addEventDTO), HttpStatus.CREATED);
    }


    @GetMapping("/{id}/team")
    ResponseEntity<TeamEventDTO> getTeamsOfEventById(@PathVariable Long id){
        return new ResponseEntity<>( eventService.getTeamsOfEventById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/sub")
    ResponseEntity<List<SubmissionDTO>> getAllsubmissionOfanEvent(@PathVariable Long id){
        return new ResponseEntity<>( eventService.getAllsubmissionOfanEvent(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/leaderboard")
    ResponseEntity<List<LeaderboardDTO>> getLeaderBoardOfanEvent(@PathVariable Long id){
        return new ResponseEntity<>( eventService.getLeaderBoardOfanEvent(id), HttpStatus.OK);
    }

}
