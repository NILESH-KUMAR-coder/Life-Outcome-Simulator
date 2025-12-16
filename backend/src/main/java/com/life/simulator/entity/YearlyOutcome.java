package com.life.simulator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "yearly_outcomes",
    indexes = @Index(
        name = "idx_outcome_sim_year",
        columnList = "simulation_id,year_number"
    )
)
public class YearlyOutcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year_number")
    private int year;

    @Column(name = "net_worth")
    private double netWorth;

    @Column(name = "income")
    private double income;

    @Column(name = "expenses")
    private double expenses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulation_id")
    private Simulation simulation;

    /* ---------- getters ---------- */

    public int getYear() {
        return year;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public double getIncome() {
        return income;
    }

    public double getExpenses() {
        return expenses;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    /* ---------- setters ---------- */

    public void setYear(int year) {
        this.year = year;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
