package com.example.practice.entity;

import com.example.practice.entity.type.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
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
    @Size(max = 8, message = "Password must be less than 9 Char..")
    private String password;

    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private List<Team> teams;


}

