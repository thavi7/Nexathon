package com.example.practice.service;

import com.example.practice.Repository.RegistrationRepository;
import com.example.practice.Repository.TeamRepository;
import com.example.practice.Repository.UserRepo;
import com.example.practice.dto.*;
import com.example.practice.entity.Registration;
import com.example.practice.entity.Team;
import com.example.practice.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeamServiceImp implements TeamService{

    private final TeamRepository teamRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final RegistrationRepository registrationRepository;

    @Override
    public List<TeamDTO> getAll() {
        List<Team> teams= teamRepo.findAll();
        List<TeamDTO> teamDTOList = teams.stream().map(TEAM ->modelMapper.map(TEAM,TeamDTO.class))
                .toList();
        return teamDTOList;
    }

    @Override
    public TeamDTO getById(Long id) {
        Team team = teamRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Team not found"));
        TeamDTO map = modelMapper.map(team, TeamDTO.class);
        return map;
    }

    @Override
    public TeamDTO createTeam(AddTeamDTO team) {
        Team newteam = modelMapper.map(team, Team.class);
        teamRepo.save(newteam);
        return modelMapper.map(newteam,TeamDTO.class);
    }

    @Override
    public TeamDTO deleteTeam(Long id) {
        Team team = teamRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Team not found"));
        teamRepo.delete(team);
        TeamDTO delteam = modelMapper.map(team,TeamDTO.class);
        return delteam;
    }



    @Override
    public List<UserDTO> getAllUsersOfTeam(Long teamId) {
        List<User> allUsersOfTeam = teamRepo.getAllUsersOfTeam(teamId);
        List<UserDTO> userDTOList = allUsersOfTeam.stream().map(USER ->modelMapper.map(USER,UserDTO.class))
                .toList();
        return userDTOList;
    }

    @Override
    @Transactional
    public TeamDTO addUserToTeam(Long teamId, Long userId) {
        Team team = teamRepo.findById(teamId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();
        //conditions
        if(team.getMembers().contains(user)){
            throw new RuntimeException("User is already added");
        }
        if(team.getMembers().size()>=4){
            throw new RuntimeException("Team is Full...");
        }
        team.getMembers().add(user);
        user.getTeams().add(team);

        TeamDTO teamDTO = modelMapper.map(team, TeamDTO.class);
        return teamDTO;

    }

    @Override
    @Transactional
    public TeamDTO removeUserFromTeam(Long teamId, Long userId) {
        Team team = teamRepo.findById(teamId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();
        if(!team.getMembers().contains(user)){
            throw new RuntimeException("User not found in this team");
        }
        team.getMembers().remove(user);
        user.getTeams().remove(team);

        TeamDTO teamDTO = modelMapper.map(team, TeamDTO.class);
        return teamDTO;
    }

    @Override
    public List<RegistrationDTO> getRegOfAteam(Long teamId) {
            List<Registration> registrationOfAnUser = registrationRepository.getRegOfAteam(teamId);
            List<RegistrationDTO> registrationDTOS = registrationOfAnUser.stream().map(REG ->modelMapper.map(REG,RegistrationDTO.class))
                    .toList();
            return registrationDTOS;

    }
}
