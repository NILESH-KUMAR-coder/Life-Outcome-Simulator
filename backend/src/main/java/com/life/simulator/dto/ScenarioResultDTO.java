package com.life.simulator.dto;

import java.util.List;

public class ScenarioResultDTO {

    public String name;
    public List<Double> values;

    public ScenarioResultDTO(String name, List<Double> values) {
        this.name = name;
        this.values = values;
    }
}
