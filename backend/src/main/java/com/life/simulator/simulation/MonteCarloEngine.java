package com.life.simulator.simulation;

import java.util.*;
import java.util.concurrent.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MonteCarloEngine {

    private final ExecutorService executor;

    public MonteCarloEngine(
            @Value("${simulation.thread-pool-size}") int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public List<SimulationPath> runPaths(long seed, int years, double riskTolerance, double income, double expenses) {

        int runs = 1000;
        List<Future<SimulationPath>> futures = new ArrayList<>(runs);

        for (int i = 0; i < runs; i++) {
            futures.add(
                executor.submit(
                    new SimulationTask(years, seed, riskTolerance, i, income, expenses)
                )
            );
        }

        List<SimulationPath> paths = new ArrayList<>(runs);
        for (Future<SimulationPath> f : futures) {
            try {
                paths.add(f.get());
            } catch (Exception e) {
                throw new RuntimeException("Simulation failure", e);
            }
        }

        return paths;
    }

    /* ===================== NEW ===================== */

    public List<YearlyDistribution> extractYearlyNetWorth(
            List<SimulationPath> paths, int years) {

        List<YearlyDistribution> result = new ArrayList<>();

        for (int year = 0; year < years; year++) {
            List<Double> values = new ArrayList<>(paths.size());
            for (SimulationPath p : paths) {
                values.add(p.getNetWorthAt(year));
            }
            result.add(new YearlyDistribution(year, values));
        }

        return result;
    }
}
