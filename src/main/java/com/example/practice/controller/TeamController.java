package com.example.practice.controller;

import com.example.practice.dto.AddTeamDTO;
import com.example.practice.dto.AddUserDTO;
import com.example.practice.dto.TeamDTO;
import com.example.practice.dto.UserDTO;
import com.example.practice.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/teams")
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping()
    ResponseEntity<List<TeamDTO>> getAllTeams(){
        return new ResponseEntity<>( teamService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<TeamDTO> getAllTeams(@PathVariable Long id){
        return new ResponseEntity<>( teamService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<TeamDTO> createUser(@Valid @RequestBody AddTeamDTO addTeamDTO){
        return new ResponseEntity<>( teamService.createTeam(addTeamDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<TeamDTO> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>( teamService.deleteTeam(id), HttpStatus.NO_CONTENT);
    }




    @GetMapping("/{id}/users")
    ResponseEntity<List<UserDTO>> getAllUsersOfTeam(@PathVariable Long id){
        return new ResponseEntity<>( teamService.getAllUsersOfTeam(id), HttpStatus.OK);
    }

    @PostMapping("/{teamid}/user/{userid}")
    ResponseEntity<TeamDTO> addUserToTeam(@PathVariable Long teamid, @PathVariable Long userid){
        return new ResponseEntity<>( teamService.addUserToTeam(teamid,userid), HttpStatus.OK);
    }

    @DeleteMapping("/{teamid}/user/{userid}")
    ResponseEntity<TeamDTO> removeUserFromTeam(@PathVariable Long teamid, @PathVariable Long userid){
        return new ResponseEntity<>( teamService.removeUserFromTeam(teamid,userid), HttpStatus.NO_CONTENT);
    }

}
