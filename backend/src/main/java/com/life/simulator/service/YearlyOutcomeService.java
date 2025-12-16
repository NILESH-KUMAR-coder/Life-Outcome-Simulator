package com.life.simulator.service;

import org.springframework.stereotype.Service;

import com.life.simulator.entity.YearlyOutcome;
import com.life.simulator.repository.YearlyOutcomeRepository;

@Service
public class YearlyOutcomeService {

    private final YearlyOutcomeRepository repo;

    public YearlyOutcomeService(YearlyOutcomeRepository repo) {
        this.repo = repo;
    }

    public void save(YearlyOutcome outcome) {
        repo.save(outcome);
    }
}
