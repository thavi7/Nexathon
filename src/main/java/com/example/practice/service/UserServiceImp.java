package com.example.practice.service;

import com.example.practice.dto.AddUserDTO;
import com.example.practice.dto.TeamDTO;
import com.example.practice.entity.Team;
import com.example.practice.entity.User;
import com.example.practice.Repository.UserRepo;
import com.example.practice.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;


    @Override
    public List<UserDTO> getAll() {
        List<User> user= userRepo.findAll();
        List<UserDTO> userDTOList = user.stream().map(USER ->modelMapper.map(USER,UserDTO.class))
                .toList();
        return userDTOList;
    }

    @Override
    public UserDTO createUser(AddUserDTO user) {
        User newuser = modelMapper.map(user, User.class);
        userRepo.save(newuser);
        return modelMapper.map(newuser,UserDTO.class);
    }

    @Override
    public UserDTO deleteUSer(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->new IllegalArgumentException("USER not found"));
        userRepo.delete(user);
        UserDTO deluser = modelMapper.map(user,UserDTO.class);
        return deluser;
    }

    @Override
    public UserDTO updateUser(Long id, AddUserDTO addUserDTO) {
        User user = userRepo.findById(id).orElseThrow(()->new IllegalArgumentException("USER not found"));
       modelMapper.map(addUserDTO,user);
        User save = userRepo.save(user);
        UserDTO newuser = modelMapper.map(save,UserDTO.class);
        return newuser;
    }

    @Override
    public UserDTO updatePartialUser(Long id, Map<String, Object> changes) {
        User user= userRepo.findById(id).orElseThrow(()->new IllegalArgumentException("USER  not found")); //DB --> local

        changes.forEach((field,value)->{ //local user me changes kiya
            switch (field){
                case "name":
                    user.setName((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
                    break;
                case "password":
                    user.setPassword((String) value);
                    break;
                default: throw new IllegalArgumentException("Filed is not supported");
            }
        });
        User save = userRepo.save(user); //fir changed user ko save kiya
        UserDTO userDTO = modelMapper.map(save,UserDTO.class);
        return userDTO;
    }



    @Override
    public List<TeamDTO> getTeamsOfAnUser(Long id) {
        List<Team> teamsOfAnUser = userRepo.getTeamsOfAnUser(id);
        List<TeamDTO> teamDTOS = teamsOfAnUser.stream().map(TEAM ->modelMapper.map(TEAM,TeamDTO.class))
                .toList();
        return teamDTOS;
    }


}
