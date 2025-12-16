package com.life.simulator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.life.simulator.dto.AggregatedSimulationResultDTO;
import com.life.simulator.dto.DecisionRequest;
import com.life.simulator.dto.SimulationRequest;
import com.life.simulator.entity.Decision;
import com.life.simulator.entity.Simulation;
import com.life.simulator.repository.DecisionRepository;
import com.life.simulator.repository.SimulationRepository;
import com.life.simulator.service.SimulationAggregationService;
import com.life.simulator.service.SimulationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    private final SimulationService service;
    private final SimulationAggregationService aggregationService;
    private final DecisionRepository decisionRepository;
    private final SimulationRepository simulationRepository;

    public SimulationController(
            SimulationService service,
            SimulationAggregationService aggregationService,
            DecisionRepository decisionRepository,
            SimulationRepository simulationRepository) {
        this.service = service;
        this.aggregationService = aggregationService;
        this.decisionRepository = decisionRepository;
        this.simulationRepository = simulationRepository;
    }

    @PostMapping("/start")
    public Simulation start(HttpServletRequest request,
                            @RequestBody SimulationRequest req) {
        Long userId = (Long) request.getAttribute("X-USER-ID");
        return service.startSimulation(userId, req.years, req.riskTolerance, req.seed, req.income, req.expenses);
    }

    @GetMapping("/results/{id}")
    public AggregatedSimulationResultDTO results(@PathVariable Long id) {
        return aggregationService.aggregate(id);
    }

    @PostMapping("/decision")
    public void applyDecision(HttpServletRequest request, @RequestBody DecisionRequest req) {
        Long userId = (Long) request.getAttribute("X-USER-ID");
        Simulation simulation = simulationRepository.findById(req.simulationId)
                .orElseThrow(() -> new RuntimeException("Simulation not found"));
        if (!simulation.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        Decision decision = new Decision();
        decision.setSimulation(simulation);
        decision.setCareer(req.career);
        decision.setSavingsRate(req.savingsRate);
        decision.setInvestmentStrategy(req.investmentStrategy);
        decision.setLifestyleCost(req.lifestyleCost);
        decision.setCategory(req.category);
        decision.setDescription(req.description);
        decision.setImpactFactor(req.impactFactor);
        decisionRepository.save(decision);
    }
}
