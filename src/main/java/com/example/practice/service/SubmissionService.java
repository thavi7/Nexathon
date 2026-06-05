package com.example.practice.service;


import com.example.practice.dto.AddSubmissionDTO;
import com.example.practice.dto.SubmissionDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface SubmissionService {

    List<SubmissionDTO> getAllsubmission();

    SubmissionDTO createSubmission( AddSubmissionDTO addSubmissionDTO);

    SubmissionDTO deleteSubmissionOfaTeam(Long teamId,Long eventId);


}
