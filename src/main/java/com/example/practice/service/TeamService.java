package com.example.practice.service;

import com.example.practice.dto.*;

import java.util.List;

public interface TeamService {
    List<TeamDTO> getAll();
    TeamDTO getById(Long id);
    TeamDTO createTeam(AddTeamDTO addTeamDTO);
    TeamDTO deleteTeam(Long id);

    List<UserDTO>getAllUsersOfTeam(Long teamId);
    TeamDTO addUserToTeam(Long teamId, Long userId);
    TeamDTO removeUserFromTeam(Long teamId, Long userId);
    List<RegistrationDTO>getRegOfAteam(Long teamId);
}
