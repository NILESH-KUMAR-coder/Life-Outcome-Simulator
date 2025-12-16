package com.life.simulator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "decisions")
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String career;

    @Column(name = "savings_rate")
    private double savingsRate;

    @Column(name = "investment_strategy")
    private String investmentStrategy;

    @Column(name = "lifestyle_cost")
    private double lifestyleCost;

    private String category;
    private String description;

    @Column(name = "impact_factor")
    private double impactFactor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulation_id")
    private Simulation simulation;

    // getters & setters

    public Long getId() { return id; }

    public String getCareer() { return career; }
    public void setCareer(String career) { this.career = career; }

    public double getSavingsRate() { return savingsRate; }
    public void setSavingsRate(double savingsRate) { this.savingsRate = savingsRate; }

    public String getInvestmentStrategy() { return investmentStrategy; }
    public void setInvestmentStrategy(String investmentStrategy) {
        this.investmentStrategy = investmentStrategy;
    }

    public double getLifestyleCost() { return lifestyleCost; }
    public void setLifestyleCost(double lifestyleCost) {
        this.lifestyleCost = lifestyleCost;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getImpactFactor() { return impactFactor; }
    public void setImpactFactor(double impactFactor) {
        this.impactFactor = impactFactor;
    }

    public Simulation getSimulation() { return simulation; }
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
