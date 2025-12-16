package com.life.simulator.dto;

public class YearlyPercentileDTO {

    public int year;
    public double p10;
    public double p50;
    public double p90;

    public YearlyPercentileDTO(int year, double p10, double p50, double p90) {
        this.year = year;
        this.p10 = p10;
        this.p50 = p50;
        this.p90 = p90;
    }
}
