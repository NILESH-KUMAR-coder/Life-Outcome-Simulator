package com.life.simulator.repository;

import com.life.simulator.entity.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DecisionRepository extends JpaRepository<Decision, Long> {
    List<Decision> findBySimulationId(Long simulationId);
}
