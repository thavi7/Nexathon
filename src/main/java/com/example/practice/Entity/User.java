package com.example.practice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Participant",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_user_name_email",columnNames = {"name","password"})
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(unique = true,nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    @Size(max = 8, message = "Password must be less than 5 Char..")
    private String password;

    @Column(updatable = false)
    private String role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @ManyToMany(mappedBy = "members")
    private List<Team> teams;

}

