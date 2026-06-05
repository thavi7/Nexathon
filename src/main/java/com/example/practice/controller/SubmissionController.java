package com.example.practice.controller;

import com.example.practice.dto.AddSubmissionDTO;
import com.example.practice.dto.SubmissionDTO;
import com.example.practice.service.SubmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sub")
@RestController
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    @GetMapping()
    ResponseEntity<List<SubmissionDTO>> getAllReg(){
        return new ResponseEntity<>( submissionService.getAllsubmission(), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<SubmissionDTO> createSubmissionOfaTeamForEvent(@Valid @RequestBody AddSubmissionDTO addSubmissionDTO){
        return new ResponseEntity<>( submissionService.createSubmission(addSubmissionDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/team/{teamId}/event/{eventId}")
    ResponseEntity<SubmissionDTO> deleteSubmissionOfaTeamForEvent(@PathVariable Long teamId,@PathVariable Long eventId){
        return new ResponseEntity<>( submissionService.deleteSubmissionOfaTeam(teamId,eventId), HttpStatus.NO_CONTENT);
    }


}
