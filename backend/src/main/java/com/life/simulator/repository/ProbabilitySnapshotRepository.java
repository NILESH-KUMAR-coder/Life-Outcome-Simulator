package com.life.simulator.repository;

import com.life.simulator.entity.ProbabilitySnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProbabilitySnapshotRepository
        extends JpaRepository<ProbabilitySnapshot, Long> {
}
