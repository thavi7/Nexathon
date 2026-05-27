package com.example.practice.entity;

import com.example.practice.entity.type.EventMode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private EventMode mode;

    private String location;


    @OneToMany(mappedBy = "event")
    private List<Team> team;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Registration>registrations;

    @OneToMany(mappedBy = "event")
    private List<Submission>submissions;
}
