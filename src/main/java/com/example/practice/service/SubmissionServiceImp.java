package com.example.practice.service;

import com.example.practice.Mapper.ModalMapper;
import com.example.practice.Repository.EventRepository;
import com.example.practice.Repository.RegistrationRepository;
import com.example.practice.Repository.SubmissionRepository;
import com.example.practice.Repository.TeamRepository;
import com.example.practice.dto.AddSubmissionDTO;
import com.example.practice.dto.SubmissionDTO;
import com.example.practice.entity.Event;
import com.example.practice.entity.Submission;
import com.example.practice.entity.Team;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImp implements SubmissionService{

    private final SubmissionRepository submissionRepository;
    private final ModelMapper modelMapper;
    private final RegistrationRepository registrationRepository;
    private final TeamRepository teamRepository;
    private final EventRepository eventRepository;

    @Override
    public List<SubmissionDTO> getAllsubmission() {
        List<Submission> all = submissionRepository.findAll();
        List<SubmissionDTO> submissionDTO = all.stream().map(SUB ->modelMapper.map(SUB, SubmissionDTO.class))
                .toList();

        return submissionDTO;
    }

    @Override
    @Transactional
    public SubmissionDTO createSubmission(AddSubmissionDTO addSubmissionDTO) {
        Long eventId = addSubmissionDTO.getEventId();
        Long teamId = addSubmissionDTO.getTeamId();
        Team team = teamRepository.findById(teamId).orElseThrow();
        Event event = eventRepository.findById(eventId).orElseThrow();
        if (!registrationRepository
                .existsByTeamIdAndEventId(
                        teamId,
                        eventId)) {

            throw new RuntimeException(
                    "Team is not registered for this event");
        }

        if (submissionRepository
                .existsByTeamIdAndEventId(
                        teamId,
                        eventId)) {

            throw new RuntimeException(
                    "Submission already exists");
        }

        Submission submission = new Submission();
        submission.setProjectTitle(addSubmissionDTO.getProjectTitle());
        submission.setProjectLink(addSubmissionDTO.getProjectLink());
        submission.setGithubLink(addSubmissionDTO.getGithubLink());
        submission.setDescription(addSubmissionDTO.getDescription());
        submission.setScore(ThreadLocalRandom.current().nextInt(1, 11));
        submission.setEvent(event);
        submission.setTeam(team);

        Submission save = submissionRepository.save(submission);

        SubmissionDTO submissionDTO = modelMapper.map(submission,SubmissionDTO.class);
        submissionDTO.setTeamID(save.getTeam().getId());
        submissionDTO.setEventId(save.getEvent().getId());
        return submissionDTO;

    }

    @Override
    public SubmissionDTO deleteSubmissionOfaTeam(Long teamId,Long eventId) {

        if (!submissionRepository
                .existsByTeamIdAndEventId(
                        teamId,
                        eventId)) {

            throw new RuntimeException(
                    "Submission does not exists");
        }
        Submission submission = submissionRepository.findByTeamIdAndEventId(teamId, eventId);
        submissionRepository.delete(submission);
        SubmissionDTO submissionDTO = modelMapper.map(submission,SubmissionDTO.class);
        return submissionDTO;
    }
}
