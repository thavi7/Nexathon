package com.example.practice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddTeamDTO {
    @NonNull
    private String teamName;
}
