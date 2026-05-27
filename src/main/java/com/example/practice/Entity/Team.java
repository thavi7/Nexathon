package com.example.practice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String teamName;

    @ManyToOne
    private Event event;

    @ManyToMany
    private List<User>members;

    @OneToMany(mappedBy = "team")
    private List<Submission> submission;

}
