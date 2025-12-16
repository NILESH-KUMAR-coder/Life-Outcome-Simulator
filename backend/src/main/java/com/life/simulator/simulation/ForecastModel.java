package com.life.simulator.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForecastModel {

    private final RiskModel riskModel;
    private final double initialIncome;
    private final double initialExpenses;

    public ForecastModel(RiskModel riskModel, double initialIncome, double initialExpenses) {
        this.riskModel = riskModel;
        this.initialIncome = initialIncome;
        this.initialExpenses = initialExpenses;
    }

    public SimulationPath simulatePath(int years, Random rng) {

        double netWorth = 10_000;
        double income = initialIncome;
        double expenses = initialExpenses;

        List<Double> path = new ArrayList<>();
        List<Double> incomePath = new ArrayList<>();
        List<Double> expensePath = new ArrayList<>();

        for (int year = 1; year <= years; year++) {
            income *= riskModel.incomeShock(rng);
            netWorth += Math.max(0, income - expenses);
            netWorth *= riskModel.investmentReturn(rng);

            expenses *= 1.02;
            income *= 1.03;

            path.add(Math.max(netWorth, 0));
            incomePath.add(income);
            expensePath.add(expenses);
        }

        return new SimulationPath(path, incomePath, expensePath);
    }
}
