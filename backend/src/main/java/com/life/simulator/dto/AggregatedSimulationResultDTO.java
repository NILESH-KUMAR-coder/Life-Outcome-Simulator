package com.life.simulator.dto;

import java.util.List;

public class AggregatedSimulationResultDTO {

    public List<YearlyPercentileDTO> yearlyPercentiles;
    public List<Double> best;
    public List<Double> worst;
    public List<Double> expected;

    public AggregatedSimulationResultDTO(
            List<YearlyPercentileDTO> yearlyPercentiles,
            List<Double> best,
            List<Double> worst,
            List<Double> expected) {
        this.yearlyPercentiles = yearlyPercentiles;
        this.best = best;
        this.worst = worst;
        this.expected = expected;
    }
}
