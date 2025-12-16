package com.life.simulator.simulation;

import java.util.Random;
import java.util.concurrent.Callable;

public class SimulationTask implements Callable<SimulationPath> {

    private final int years;
    private final long seed;
    private final double riskTolerance;
    private final int index;
    private final double income;
    private final double expenses;

    public SimulationTask(int years, long seed, double riskTolerance, int index, double income, double expenses) {
        this.years = years;
        this.seed = seed;
        this.riskTolerance = riskTolerance;
        this.index = index;
        this.income = income;
        this.expenses = expenses;
    }

    @Override
    public SimulationPath call() {
        Random rng = new Random(seed + index);
        RiskModel risk = new RiskModel(riskTolerance);
        ForecastModel forecast = new ForecastModel(risk, income, expenses);
        return forecast.simulatePath(years, rng);
    }
}
