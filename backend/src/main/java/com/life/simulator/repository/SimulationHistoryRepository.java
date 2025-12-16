package com.life.simulator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.life.simulator.entity.SimulationHistory;

public interface SimulationHistoryRepository extends JpaRepository<SimulationHistory, Long> {
    List<SimulationHistory> findByUserId(Long userId);
}
