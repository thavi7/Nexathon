package com.example.practice.service;

import com.example.practice.Repository.EventRepository;
import com.example.practice.Repository.RegistrationRepository;
import com.example.practice.Repository.UserRepo;
import com.example.practice.dto.AddRegistrationDTO;
import com.example.practice.dto.RegistrationDTO;
import com.example.practice.entity.Event;
import com.example.practice.entity.Registration;
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
    private final UserRepo userRepo;
    private final EventRepository eventRepository;


    @Override
    public List<RegistrationDTO> getAllReg() {
        List<Registration> all = registrationRepository.findAll();

        List<RegistrationDTO> registrationDTOS = all.stream().map(reg ->{

            RegistrationDTO registrationDTO=new RegistrationDTO();
            registrationDTO.setId(reg.getId());
            registrationDTO.setStatus(reg.getStatus());
            registrationDTO.setRegisteredAt(reg.getRegisteredAt());
            registrationDTO.setUsername(reg.getUser().getName());
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
        registrationDTO.setUsername(reg.getUser().getName());
        registrationDTO.setEventname(reg.getEvent().getTitle());
        return registrationDTO;
    }

    @Override
    @Transactional
    public RegistrationDTO createReg(AddRegistrationDTO addRegistrationDTO) {
        User user = userRepo.findById(addRegistrationDTO.getUser_id()).orElseThrow();
        Event event = eventRepository.findById(addRegistrationDTO.getEvent_id()).orElseThrow();
        Registration registration=new Registration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegisteredAt(LocalDateTime.now());
        registration.setStatus(StatusType.PENDING);

        Registration save = registrationRepository.save(registration);
        RegistrationDTO dto = new RegistrationDTO();

        dto.setId(save.getId());
        dto.setRegisteredAt(save.getRegisteredAt());
        dto.setStatus(save.getStatus());
        dto.setUsername(save.getUser().getName());
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
        registrationDTO.setUsername(reg.getUser().getName());
        registrationDTO.setEventname(reg.getEvent().getTitle());
        registrationRepository.delete(reg);
        return registrationDTO;
    }

    @Override
    public List<RegistrationDTO> getAllRegOfAnUser(Long userid) {
        List<Registration> all = registrationRepository.getAllRegOfAnUser(userid);
        List<RegistrationDTO> registrationDTOS = all.stream().map(reg ->{

                    RegistrationDTO registrationDTO=new RegistrationDTO();
                    registrationDTO.setId(reg.getId());
                    registrationDTO.setStatus(reg.getStatus());
                    registrationDTO.setRegisteredAt(reg.getRegisteredAt());
                    registrationDTO.setUsername(reg.getUser().getName());
                    registrationDTO.setEventname(reg.getEvent().getTitle());
                    return registrationDTO;
                })
                .toList();

        return registrationDTOS;
    }

    @Override
    public List<RegistrationDTO> getAllRegOfAnEvent(Long eventid) {
        List<Registration> all = registrationRepository.getAllRegOfAnEvent(eventid);
        List<RegistrationDTO> registrationDTOS = all.stream().map(reg ->{

                    RegistrationDTO registrationDTO=new RegistrationDTO();
                    registrationDTO.setId(reg.getId());
                    registrationDTO.setStatus(reg.getStatus());
                    registrationDTO.setRegisteredAt(reg.getRegisteredAt());
                    registrationDTO.setUsername(reg.getUser().getName());
                    registrationDTO.setEventname(reg.getEvent().getTitle());
                    return registrationDTO;
                })
                .toList();

        return registrationDTOS;
    }
}
