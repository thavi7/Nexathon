package com.example.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddSubmissionDTO {

    private Long teamId;

    private Long eventId;

    private String projectTitle;

    private String githubLink;

    private String projectLink;

    private String description;

}
