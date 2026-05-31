package com.example.practice.Repository;

import com.example.practice.entity.Registration;
import com.example.practice.entity.Team;
import com.example.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

        @Query("SELECT DISTINCT t FROM Team t LEFT JOIN t.members u WHERE u.id=:id")
        List<Team> getTeamsOfAnUser(@Param("id") Long user_id);


}
