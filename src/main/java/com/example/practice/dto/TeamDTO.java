package com.example.practice.dto;


import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamDTO {
    private Long id;
    private String teamName;
    private List<String> memberNames;
}
