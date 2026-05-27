package com.example.practice.dto;


import com.example.practice.entity.type.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserDTO {
    @NonNull
    @Column(nullable = false)
    private String name;
    @NonNull
    @Column(unique = true,nullable = false)
    private String email;
    @NonNull
    @Column(nullable = false)
    private String password;
    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;
}
