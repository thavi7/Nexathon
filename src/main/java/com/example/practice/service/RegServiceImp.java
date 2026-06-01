package com.example.practice.service;

import com.example.practice.Repository.EventRepository;
import com.example.practice.Repository.RegistrationRepository;
import com.example.practice.Repository.TeamRepository;
import com.example.practice.Repository.UserRepo;
import com.example.practice.dto.AddRegistrationDTO;
import com.example.practice.dto.RegistrationDTO;
import com.example.practice.entity.Event;
import com.example.practice.entity.Registration;
import com.example.practice.entity.Team;
import com.example.practice.entity.User;
import com.example.practice.entity.type.StatusType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegServiceImp implements RegService{

    private final RegistrationRepository registrationRepository;
    private final TeamRepository teamRepository;
    private final EventRepository eventRepository;


    @Override
    public List<RegistrationDTO> getAllReg() {
        List<Registration> all = registrationRepository.findAll();

        List<RegistrationDTO> registrationDTOS = all.stream().map(reg ->{

            RegistrationDTO registrationDTO=new RegistrationDTO();
            registrationDTO.setId(reg.getId());
            registrationDTO.setStatus(reg.getStatus());
            registrationDTO.setRegisteredAt(reg.getRegisteredAt());
            registrationDTO.setTeamname(reg.getTeam().getTeamName());
            registrationDTO.setEventname(reg.getEvent().getTitle());
            return registrationDTO;
                })
                .toList();

        return registrationDTOS;
    }

    @Override
    public RegistrationDTO getRegById(Long id) {
        Registration reg = registrationRepository.findById(id).orElseThrow();
        RegistrationDTO registrationDTO=new RegistrationDTO();
        registrationDTO.setId(reg.getId());
        registrationDTO.setStatus(reg.getStatus());
        registrationDTO.setRegisteredAt(reg.getRegisteredAt());
        registrationDTO.setTeamname(reg.getTeam().getTeamName());
        registrationDTO.setEventname(reg.getEvent().getTitle());
        return registrationDTO;
    }

    @Override
    @Transactional
    public RegistrationDTO createReg(AddRegistrationDTO addRegistrationDTO) {
        Team team = teamRepository.findById(addRegistrationDTO.getTeam_id()).orElseThrow();
        Event event = eventRepository.findById(addRegistrationDTO.getEvent_id()).orElseThrow();

        boolean alreadyRegistered = registrationRepository.findAll()
                .stream()
                .anyMatch(reg ->
                        reg.getTeam().getId().equals(team.getId()) &&
                                reg.getEvent().getId().equals(event.getId()));

        if (alreadyRegistered) {
            throw new RuntimeException("Team already registered for this event");
        }

        Registration registration=new Registration();
        registration.setTeam(team);
        registration.setEvent(event);
        registration.setRegisteredAt(LocalDateTime.now());
        registration.setStatus(StatusType.PENDING);

        Registration save = registrationRepository.save(registration);
        RegistrationDTO dto = new RegistrationDTO();

        dto.setId(save.getId());
        dto.setRegisteredAt(save.getRegisteredAt());
        dto.setStatus(save.getStatus());
        dto.setTeamname(save.getTeam().getTeamName());
        dto.setEventname(save.getEvent().getTitle());

        return dto;

    }

    @Override
    public RegistrationDTO deleteReg(Long id) {
        Registration reg = registrationRepository.findById(id).orElseThrow();

        RegistrationDTO registrationDTO=new RegistrationDTO();
        registrationDTO.setId(reg.getId());
        registrationDTO.setStatus(reg.getStatus());
        registrationDTO.setRegisteredAt(reg.getRegisteredAt());
        registrationDTO.setTeamname(reg.getTeam().getTeamName());
        registrationDTO.setEventname(reg.getEvent().getTitle());
        registrationRepository.delete(reg);
        return registrationDTO;
    }


}
