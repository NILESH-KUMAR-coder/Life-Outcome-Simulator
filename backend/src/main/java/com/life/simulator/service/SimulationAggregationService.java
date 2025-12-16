package com.life.simulator.service;

import com.life.simulator.dto.*;
import com.life.simulator.entity.Simulation;
import com.life.simulator.repository.SimulationRepository;
import com.life.simulator.simulation.*;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimulationAggregationService {

    private final SimulationRepository repo;
    private final MonteCarloEngine engine;

    public SimulationAggregationService(
            SimulationRepository repo,
            MonteCarloEngine engine) {
        this.repo = repo;
        this.engine = engine;
    }

    public AggregatedSimulationResultDTO aggregate(Long simulationId) {

        Simulation sim = repo.findById(simulationId)
                .orElseThrow(() -> new RuntimeException("Simulation not found"));

        List<SimulationPath> paths = engine.runPaths(
                sim.getSeed(),
                sim.getYears(),
                sim.getRiskTolerance(),
                sim.getIncome(),
                sim.getExpenses()
        );

        int years = sim.getYears();
        List<YearlyPercentileDTO> percentiles = new ArrayList<>();
        List<Double> best = new ArrayList<>();
        List<Double> worst = new ArrayList<>();
        List<Double> expected = new ArrayList<>();

        for (int y = 0; y < years; y++) {
            List<Double> values = new ArrayList<>();

            for (SimulationPath p : paths) {
                values.add(p.getYearlyNetWorth().get(y));
            }

            Collections.sort(values);

            double p10 = values.get((int)(0.10 * values.size()));
            double p50 = values.get((int)(0.50 * values.size()));
            double p90 = values.get((int)(0.90 * values.size()));

            percentiles.add(new YearlyPercentileDTO(y + 1, p10, p50, p90));

            worst.add(values.get(0));
            best.add(values.get(values.size() - 1));
            expected.add(
                values.stream().mapToDouble(Double::doubleValue).average().orElse(0)
            );
        }

        return new AggregatedSimulationResultDTO(
                percentiles, best, worst, expected
        );
    }
}
