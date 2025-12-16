package com.life.simulator.simulation;

import java.util.Random;

public class RiskModel {

    private final double riskTolerance;

    public RiskModel(double riskTolerance) {
        this.riskTolerance = riskTolerance;
    }

    /**
     * Returns annual investment return multiplier
     */
    public double investmentReturn(Random rng) {
        double volatility = 0.05 + (0.25 * riskTolerance);
        double mean = 0.04 + (0.06 * riskTolerance);
        return 1.0 + (mean + rng.nextGaussian() * volatility);
    }

    public double incomeShock(Random rng) {
        if (rng.nextDouble() < 0.05 * riskTolerance) {
            return 0.7; // job loss / downturn
        }
        if (rng.nextDouble() < 0.1 * riskTolerance) {
            return 1.3; // promotion / success
        }
        return 1.0;
    }
}
