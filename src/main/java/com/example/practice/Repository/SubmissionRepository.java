package com.example.practice.Repository;

import com.example.practice.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    boolean existsByTeamIdAndEventId(Long teamId,Long eventId);
    Submission findByTeamIdAndEventId(Long teamId,Long eventId);
    List<Submission> findByTeamId(Long teamId);
    List<Submission> findByEventIdOrderByScoreDesc(Long eventId);
}