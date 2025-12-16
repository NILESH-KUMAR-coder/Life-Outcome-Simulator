package com.life.simulator.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "simulations")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long seed;
    private int years;
    private double riskTolerance;
    private double income;
    private double expenses;

    private Instant createdAt = Instant.now();

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getSeed() { return seed; }
    public int getYears() { return years; }
    public double getRiskTolerance() { return riskTolerance; }
    public double getIncome() { return income; }
    public double getExpenses() { return expenses; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setSeed(Long seed) { this.seed = seed; }
    public void setYears(int years) { this.years = years; }
    public void setRiskTolerance(double riskTolerance) { this.riskTolerance = riskTolerance; }
    public void setIncome(double income) { this.income = income; }
    public void setExpenses(double expenses) { this.expenses = expenses; }
}
