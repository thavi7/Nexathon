package com.example.practice.service;

import com.example.practice.dto.AddRegistrationDTO;
import com.example.practice.dto.RegistrationDTO;
import com.example.practice.entity.Registration;

import java.util.List;

public interface RegService {
        List<RegistrationDTO> getAllReg();
        RegistrationDTO getRegById(Long id);
        RegistrationDTO createReg(AddRegistrationDTO addRegistrationDTO);
        RegistrationDTO deleteReg(Long id);

}
