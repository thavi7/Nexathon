package com.example.practice.dto;

import com.example.practice.entity.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationDTO {
    private Long id;

    private LocalDateTime registeredAt;

    private StatusType status;

    private String teamname;

    private String eventname;
}
