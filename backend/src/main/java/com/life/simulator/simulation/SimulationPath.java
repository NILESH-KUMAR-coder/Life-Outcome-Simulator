package com.life.simulator.simulation;

import java.util.ArrayList;
import java.util.List;

public class SimulationPath {

    private final List<Double> yearlyNetWorth;
    private final List<Double> yearlyIncome;
    private final List<Double> yearlyExpenses;

    // Constructor that accepts yearly net worth values
    public SimulationPath(List<Double> yearlyNetWorth, List<Double> yearlyIncome, List<Double> yearlyExpenses) {
        this.yearlyNetWorth = yearlyNetWorth;
        this.yearlyIncome = yearlyIncome;
        this.yearlyExpenses = yearlyExpenses;
    }

    // Getter method to retrieve yearly net worth values
    public List<Double> getYearlyNetWorth() {
        return yearlyNetWorth;
    }

    // Getter method to retrieve yearly income values
    public List<Double> getYearlyIncome() {
        return yearlyIncome;
    }

    // Getter method to retrieve yearly expenses values
    public List<Double> getYearlyExpenses() {
        return yearlyExpenses;
    }

    // Getter method to retrieve net worth at a specific year (by index)
    public double getNetWorthAt(int yearIndex) {
        return yearlyNetWorth.get(yearIndex);
    }

    // Utility method to aggregate yearly net worth from multiple paths
    public static List<List<Double>> extractYearlyNetWorth(
            List<SimulationPath> paths, int years) {

        // Prepare a list to hold net worth values for each year
        List<List<Double>> yearly = new ArrayList<>();

        // Initialize a list for each year
        for (int y = 0; y < years; y++) {
            yearly.add(new ArrayList<>());
        }

        // Aggregate net worth from all paths for each year
        for (SimulationPath p : paths) {
            for (int y = 0; y < years; y++) {
                yearly.get(y).add(p.getNetWorthAt(y));
            }
        }

        return yearly;
    }

    // Utility method to aggregate yearly income from multiple paths
    public static List<List<Double>> extractYearlyIncome(
            List<SimulationPath> paths, int years) {

        List<List<Double>> yearly = new ArrayList<>();
        for (int y = 0; y < years; y++) {
            yearly.add(new ArrayList<>());
        }

        for (SimulationPath p : paths) {
            for (int y = 0; y < years; y++) {
                yearly.get(y).add(p.getYearlyIncome().get(y));
            }
        }

        return yearly;
    }

    // Utility method to aggregate yearly expenses from multiple paths
    public static List<List<Double>> extractYearlyExpenses(
            List<SimulationPath> paths, int years) {

        List<List<Double>> yearly = new ArrayList<>();
        for (int y = 0; y < years; y++) {
            yearly.add(new ArrayList<>());
        }

        for (SimulationPath p : paths) {
            for (int y = 0; y < years; y++) {
                yearly.get(y).add(p.getYearlyExpenses().get(y));
            }
        }

        return yearly;
    }
}
