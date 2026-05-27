package com.example.practice.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubmissionDTO {
    private Long id;

    private String projectTitle;

    private String githubLink;

    private String projectLink;

    private String description;

    private Integer score;

    private LocalDateTime submittedAt;
}
