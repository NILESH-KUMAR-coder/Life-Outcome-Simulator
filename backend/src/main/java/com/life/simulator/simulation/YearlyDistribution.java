package com.life.simulator.simulation;

import java.util.List;

public class YearlyDistribution {

    public final int year;
    public final List<Double> values;

    public YearlyDistribution(int year, List<Double> values) {
        this.year = year;
        this.values = values;
    }
}
