package com.example.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaderboardDTO {

    private Integer rank;

    private Long teamId;

    private String teamName;

    private Integer score;

}
