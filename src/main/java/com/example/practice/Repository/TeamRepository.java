package com.example.practice.Repository;

import com.example.practice.entity.Team;
import com.example.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT DISTINCT u FROM Team t JOIN t.members u WHERE t.id=:id")
    List<User> getAllUsersOfTeam(@Param("id") Long team_id);


}