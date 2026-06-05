package com.example.practice.service;

import com.example.practice.Repository.RegistrationRepository;
import com.example.practice.Repository.SubmissionRepository;
import com.example.practice.Repository.TeamRepository;
import com.example.practice.Repository.UserRepo;
import com.example.practice.dto.*;
import com.example.practice.entity.Registration;
import com.example.practice.entity.Submission;
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
    private final SubmissionRepository submissionRepository;

    @Override
    public List<TeamDTO> getAll() {
        List<Team> teams= teamRepo.findAll();
        List<TeamDTO> teamDTOList = teams.stream()
                .map(team -> {
                    TeamDTO dto = modelMapper.map(team, TeamDTO.class);

                    dto.setMemberNames(
                            team.getMembers()
                                    .stream()
                                    .map(User::getName)
                                    .toList()
                    );

                    return dto;
                })
                .toList();
        return teamDTOList;
    }

    @Override
    public TeamDTO getById(Long id) {
        Team team = teamRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Team not found"));
        TeamDTO map = modelMapper.map(team, TeamDTO.class);
        map.setMemberNames(
                team.getMembers()
                        .stream()
                        .map(User::getName)
                        .toList()
        );
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
        delteam.setMemberNames(
                team.getMembers()
                        .stream()
                        .map(User::getName)
                        .toList()
        );
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
        teamDTO.setMemberNames(
                team.getMembers()
                        .stream()
                        .map(User::getName)
                        .toList()
        );
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
        teamDTO.setMemberNames(
                team.getMembers()
                        .stream()
                        .map(User::getName)
                        .toList()
        );
        return teamDTO;
    }


    private RegistrationDTO convertToDTO(Registration reg) {

        RegistrationDTO dto = new RegistrationDTO();

        dto.setId(reg.getId());
        dto.setRegisteredAt(reg.getRegisteredAt());
        dto.setStatus(reg.getStatus());

        if (reg.getTeam() != null) {
            dto.setTeamname(reg.getTeam().getTeamName());
        }

        if (reg.getEvent() != null) {
            dto.setEventname(reg.getEvent().getTitle());
        }

        return dto;
    }

    @Override
    public List<RegistrationDTO> getRegOfAteam(Long teamId) {
            List<Registration> registrationOfAnUser = registrationRepository.getRegOfAteam(teamId);
            List<RegistrationDTO> registrationDTOS = registrationOfAnUser.stream()
                    .map(this::convertToDTO)
                    .toList();
            return registrationDTOS;

    }

    @Override
    public List<SubmissionDTO> getAllsubmissionOfaTeam(Long id) {
            List<Submission> submission = submissionRepository.findByTeamId(id);
            List<SubmissionDTO> submissionDTO = submission.stream().map(SUB ->modelMapper.map(SUB, SubmissionDTO.class))
                    .toList();

            return submissionDTO;

    }
}
