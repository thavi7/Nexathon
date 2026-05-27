package com.example.practice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true,nullable = false)
    private String projectTitle;

    @NonNull
    @Column(unique = true,nullable = false)
    private String githubLink;

    @NonNull
    @Column(unique = true,nullable = false)
    private String projectLink;

    @NonNull
    @Column(nullable = false)
    private String description;

    @NonNull
    @Column(nullable = false)
    private Integer score;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
