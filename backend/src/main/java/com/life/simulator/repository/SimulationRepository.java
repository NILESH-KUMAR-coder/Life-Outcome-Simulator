package com.life.simulator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.life.simulator.entity.Simulation;

public interface SimulationRepository extends JpaRepository<Simulation, Long> {
    List<Simulation> findByUserId(Long userId);
}
