package com.example.practice.dto;


import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamDTO {
    private Long id;
    private String teamName;
}
