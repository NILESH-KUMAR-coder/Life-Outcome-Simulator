package com.life.simulator.controller;

import com.life.simulator.dto.ScenarioResultDTO;
import com.life.simulator.service.ScenarioComparisonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulation")
public class ScenarioComparisonController {

    private final ScenarioComparisonService service;

    public ScenarioComparisonController(ScenarioComparisonService service) {
        this.service = service;
    }

    @GetMapping("/{id}/scenarios")
    public List<ScenarioResultDTO> compare(@PathVariable Long id) {
        return service.compare(id);
    }
}
