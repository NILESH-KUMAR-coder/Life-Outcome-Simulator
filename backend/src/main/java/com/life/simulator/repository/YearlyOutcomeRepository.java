package com.life.simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.life.simulator.entity.YearlyOutcome;

public interface YearlyOutcomeRepository
        extends JpaRepository<YearlyOutcome, Long> {
}
