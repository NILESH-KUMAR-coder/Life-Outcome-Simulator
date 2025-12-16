package com.life.simulator.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "simulation_history")
public class SimulationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long simulationId;

    private Instant runAt = Instant.now();

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getSimulationId() { return simulationId; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setSimulationId(Long simulationId) { this.simulationId = simulationId; }
}
