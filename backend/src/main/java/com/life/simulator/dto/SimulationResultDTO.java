package com.life.simulator.dto;

import java.util.List;

public class SimulationResultDTO {

    public String scenario;          // Base / Best / Worst / Custom

    public double p10;
    public double p50;
    public double p90;

    public List<Double> rawResults;

    public SimulationResultDTO(String scenario, List<Double> sortedResults) {
        this.scenario = scenario;
        this.rawResults = sortedResults;

        int n = sortedResults.size();
        this.p10 = sortedResults.get((int) (0.10 * n));
        this.p50 = sortedResults.get((int) (0.50 * n));
        this.p90 = sortedResults.get((int) (0.90 * n));
    }
}
