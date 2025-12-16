package com.life.simulator.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.life.simulator.dto.SimulationResultDTO;
import com.life.simulator.entity.ProbabilitySnapshot;
import com.life.simulator.entity.Simulation;
import com.life.simulator.entity.SimulationHistory;
import com.life.simulator.entity.YearlyOutcome;
import com.life.simulator.repository.SimulationHistoryRepository;
import com.life.simulator.repository.SimulationRepository;
import com.life.simulator.simulation.MonteCarloEngine;
import com.life.simulator.simulation.SimulationPath;

@Service
public class SimulationService {

    private final SimulationRepository simulationRepo;
    private final SimulationHistoryRepository historyRepo;
    private final MonteCarloEngine engine;
    private final ProbabilitySnapshotService snapshotService;
    private final YearlyOutcomeService outcomeService;

    public SimulationService(
        SimulationRepository simulationRepo,
        SimulationHistoryRepository historyRepo,
        MonteCarloEngine engine,
        ProbabilitySnapshotService snapshotService,
        YearlyOutcomeService outcomeService) {

        this.simulationRepo = simulationRepo;
        this.historyRepo = historyRepo;
        this.engine = engine;
        this.snapshotService = snapshotService;
        this.outcomeService = outcomeService;
    }

    public Simulation startSimulation(
            Long userId,
            int years,
            double risk,
            Long seed,
            double income,
            double expenses) {

        // 1️⃣ Persist simulation metadata
        Simulation sim = new Simulation();
        sim.setUserId(userId);
        sim.setYears(years);
        sim.setRiskTolerance(risk);
        sim.setSeed(seed);
        sim.setIncome(income);
        sim.setExpenses(expenses);

        Simulation saved = simulationRepo.save(sim);

        // 2️⃣ Persist history
        SimulationHistory history = new SimulationHistory();
        history.setUserId(userId);
        history.setSimulationId(saved.getId());
        historyRepo.save(history);

        // 3️⃣ Run Monte Carlo
        List<SimulationPath> paths =
                engine.runPaths(seed, years, risk, income, expenses);

        // 4️⃣ Extract yearly net worth distributions
        List<List<Double>> yearlyResults =
                SimulationPath.extractYearlyNetWorth(paths, years);

        List<List<Double>> yearlyIncome =
                SimulationPath.extractYearlyIncome(paths, years);

        List<List<Double>> yearlyExpenses =
                SimulationPath.extractYearlyExpenses(paths, years);

        // 5️⃣ Persist snapshots + yearly outcomes for each scenario
        String[] scenarios = {"Base", "Best", "Worst", "Custom"};
        for (int year = 0; year < yearlyResults.size(); year++) {

            List<Double> results = yearlyResults.get(year);
            results.sort(Double::compareTo);

            List<Double> incomes = yearlyIncome.get(year);
            incomes.sort(Double::compareTo);

            List<Double> expensesList = yearlyExpenses.get(year);
            expensesList.sort(Double::compareTo);

            // Create the results for each scenario
            List<SimulationResultDTO> scenarioResults = new ArrayList<>();
            for (String scenario : scenarios) {
                // Create SimulationResultDTO for each scenario
                SimulationResultDTO dto = new SimulationResultDTO(scenario, results);
                scenarioResults.add(dto);
            }

            // 📊 Persist probability snapshots for each scenario
            snapshotService.persistSnapshots(saved, year + 1, scenarioResults);

            // 📈 Yearly outcome (using median for simplicity)
            for (SimulationResultDTO dto : scenarioResults) {
                YearlyOutcome outcome = new YearlyOutcome();
                outcome.setSimulation(saved);
                outcome.setYear(year + 1);
                outcome.setNetWorth(dto.p50); // Using median for simplicity
                outcome.setIncome(incomes.get(incomes.size() / 2));    // median income
                outcome.setExpenses(expensesList.get(expensesList.size() / 2));  // median expenses

                outcomeService.save(outcome);
            }
        }

        return saved;
    }
}
