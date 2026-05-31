package com.example.practice.Repository;

import com.example.practice.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

        @Query("SELECT r FROM Registration r WHERE r.team.id=:id ")
        List<Registration>getRegOfAteam(@Param("id") Long team_id);

        @Query("SELECT r FROM Registration r WHERE r.event.id=:id ")
        List<Registration>getAllRegOfAnEvent(@Param("id") Long event_id);

}