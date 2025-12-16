package com.life.simulator.simulation;

import java.util.List;

public class SimulationResult {

    private final List<Double> finalNetWorths;

    public SimulationResult(List<Double> finalNetWorths) {
        this.finalNetWorths = finalNetWorths;
    }

    public double percentile(double p) {
        int index = (int) Math.ceil(p * finalNetWorths.size()) - 1;
        return finalNetWorths.get(Math.max(0, Math.min(index, finalNetWorths.size() - 1)));
    }

    public double bestCase() {
        return finalNetWorths.get(finalNetWorths.size() - 1);
    }

    public double worstCase() {
        return finalNetWorths.get(0);
    }

    public double expected() {
        return finalNetWorths.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }
}
