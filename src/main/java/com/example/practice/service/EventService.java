package com.example.practice.service;

import com.example.practice.dto.*;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface EventService {
    List<EventDTO> getAllEvent();
    EventDTO getEventById(Long id);
    EventDTO createEvent(AddEventDTO eventDTO);
    EventDTO deleteEvent(Long id);
    EventDTO updateEvent(Long id,AddEventDTO addEventDTO);

    TeamEventDTO getTeamsOfEventById(Long id);
}
