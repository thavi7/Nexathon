package com.example.practice.service;

import com.example.practice.dto.AddUserDTO;
import com.example.practice.dto.UserDTO;

import java.util.List;
import java.util.Map;


public interface UserService {
    List<UserDTO> getAll();
    UserDTO createUser(AddUserDTO addUserDTO);
    UserDTO deleteUSer(Long id);
    UserDTO updateUser(Long id,AddUserDTO addUserDTO);
    UserDTO updatePartialUser(Long id, Map<String, Object> changes);
}
