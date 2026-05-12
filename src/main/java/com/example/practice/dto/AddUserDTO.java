package com.example.practice.dto;


import jakarta.persistence.Column;
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
    private String role;
}
