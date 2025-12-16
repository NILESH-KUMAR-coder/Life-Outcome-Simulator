package com.life.simulator.service;

import com.life.simulator.dto.ScenarioResultDTO;
import com.life.simulator.entity.Decision;
import com.life.simulator.repository.DecisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenarioComparisonService {

    private final DecisionRepository decisionRepo;

    public ScenarioComparisonService(DecisionRepository decisionRepo) {
        this.decisionRepo = decisionRepo;
    }

    public List<ScenarioResultDTO> compare(Long simulationId) {

        List<Decision> decisions = decisionRepo.findBySimulationId(simulationId);

        List<Double> base = new ArrayList<>();
        List<Double> best = new ArrayList<>();
        List<Double> worst = new ArrayList<>();
        List<Double> custom = new ArrayList<>();

        double factor = decisions.stream()
                .mapToDouble(Decision::getImpactFactor)
                .sum();

        for (int year = 1; year <= 30; year++) {
            double baseValue = year * 50_000;
            base.add(baseValue);
            best.add(baseValue * 1.4);
            worst.add(baseValue * 0.7);
            custom.add(baseValue * (1 + factor * 0.05));
        }

        return List.of(
            new ScenarioResultDTO("Base", base),
            new ScenarioResultDTO("Best", best),
            new ScenarioResultDTO("Worst", worst),
            new ScenarioResultDTO("Custom", custom)
        );
    }
}
