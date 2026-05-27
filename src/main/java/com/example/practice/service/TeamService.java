package com.example.practice.service;

import com.example.practice.dto.AddTeamDTO;
import com.example.practice.dto.AddUserDTO;
import com.example.practice.dto.TeamDTO;
import com.example.practice.dto.UserDTO;

import java.util.List;

public interface TeamService {
    List<TeamDTO> getAll();
    TeamDTO getById(Long id);
    TeamDTO createTeam(AddTeamDTO addTeamDTO);
    TeamDTO deleteTeam(Long id);

    List<UserDTO>getAllUsersOfTeam(Long teamId);
    TeamDTO addUserToTeam(Long teamId, Long userId);
    TeamDTO removeUserFromTeam(Long teamId, Long userId);
}
