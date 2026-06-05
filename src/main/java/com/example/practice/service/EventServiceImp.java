package com.example.practice.service;

import com.example.practice.Mapper.ModalMapper;
import com.example.practice.Repository.EventRepository;
import com.example.practice.Repository.RegistrationRepository;
import com.example.practice.Repository.SubmissionRepository;
import com.example.practice.dto.*;
import com.example.practice.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService{

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final SubmissionRepository submissionRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<EventDTO> getAllEvent() {
        List<Event> events= eventRepository.findAll();
        List<EventDTO> eventDTOS = events.stream().map(EVENT ->modelMapper.map(EVENT,EventDTO.class))
                .toList();
        return eventDTOS;
    }

    @Override
    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Event not found"));
        EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
        return eventDTO;
    }

    @Override
    public EventDTO createEvent(AddEventDTO eventDTO) {
        Event newevent = modelMapper.map(eventDTO, Event.class);
        eventRepository.save(newevent);
        return modelMapper.map(newevent,EventDTO.class);
    }

    @Override
    public EventDTO deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Event not found"));
        eventRepository.delete(event);
        EventDTO eventDTO = modelMapper.map(event,EventDTO.class);
        return eventDTO;
    }

    @Override
    public EventDTO updateEvent(Long id, AddEventDTO addEventDTO) {
        Event event = eventRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Event not found"));
        modelMapper.map(addEventDTO,event);
        Event save = eventRepository.save(event);
        EventDTO newevent = modelMapper.map(save,EventDTO.class);
        return newevent;
    }


    private TeamDTO convertToDTO(Team team) {

        TeamDTO dto = modelMapper.map(team, TeamDTO.class);

        dto.setMemberNames(
                team.getMembers()
                        .stream()
                        .map(User::getName)
                        .toList()
        );

        return dto;
    }
    @Override
    public TeamEventDTO getTeamsOfEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Event not found"));
        TeamEventDTO dto = new TeamEventDTO();

        dto.setId(event.getId());
        dto.setEventTitle(event.getTitle());

        List<TeamDTO> teams = event.getRegistrations()
                .stream()
                .map(Registration::getTeam)
                .distinct()
                .map(this::convertToDTO)
                .toList();

        dto.setTeams(teams);


        return dto;
    }

    @Override
    public List<SubmissionDTO> getAllsubmissionOfanEvent(Long id) {
        List<Submission> submission = submissionRepository.findByEventIdOrderByScoreDesc(id);
        List<SubmissionDTO> submissionDTO = submission.stream().map(SUB ->modelMapper.map(SUB, SubmissionDTO.class))
                .toList();

        return submissionDTO;
    }

    @Override
    public List<LeaderboardDTO> getLeaderBoardOfanEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        List<Submission> s = submissionRepository.findByEventIdOrderByScoreDesc(id);
        AtomicInteger rank = new AtomicInteger(1);
        List<LeaderboardDTO> leaderboard = s.stream().map(sub->{
            LeaderboardDTO dto=new LeaderboardDTO();
            dto.setRank(rank.getAndIncrement());
            dto.setScore(sub.getScore());
            dto.setTeamId(sub.getTeam().getId());
            dto.setTeamName(sub.getTeam().getTeamName());
            return dto;
        }).toList();

        return leaderboard;
    }


}
