package com.example.practice.dto;

import com.example.practice.entity.Team;
import com.example.practice.entity.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamEventDTO {

    private Long id;

    private String eventTitle;

    private List<TeamDTO> teams;

}
