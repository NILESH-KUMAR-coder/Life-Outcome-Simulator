package com.life.simulator.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "probability_snapshots",
    indexes = @Index(name = "idx_snapshot_sim_year", columnList = "simulation_id,year")
)
public class ProbabilitySnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;

    // Raw percentiles
    private double p10;
    private double p50;
    private double p90;

    // Distribution bounds (fan chart)
    @Column(name = "percentile_10")
    private double percentile10;

    @Column(name = "percentile_50")
    private double percentile50;

    @Column(name = "percentile_90")
    private double percentile90;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulation_id", nullable = false)
    private Simulation simulation;

    /* ===== getters ===== */

    public int getYear() { return year; }
    public double getP10() { return p10; }
    public double getP50() { return p50; }
    public double getP90() { return p90; }

    public double getPercentile10() { return percentile10; }
    public double getPercentile50() { return percentile50; }
    public double getPercentile90() { return percentile90; }

    public Simulation getSimulation() { return simulation; }

    /* ===== setters ===== */

    public void setYear(int year) { this.year = year; }
    public void setP10(double p10) { this.p10 = p10; }
    public void setP50(double p50) { this.p50 = p50; }
    public void setP90(double p90) { this.p90 = p90; }

    public void setPercentile10(double v) { this.percentile10 = v; }
    public void setPercentile50(double v) { this.percentile50 = v; }
    public void setPercentile90(double v) { this.percentile90 = v; }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
