package com.life.simulator.service;

import com.life.simulator.dto.SimulationResultDTO;
import com.life.simulator.entity.ProbabilitySnapshot;
import com.life.simulator.entity.Simulation;
import com.life.simulator.repository.ProbabilitySnapshotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProbabilitySnapshotService {

    private final ProbabilitySnapshotRepository repo;

    public ProbabilitySnapshotService(ProbabilitySnapshotRepository repo) {
        this.repo = repo;
    }

    public void persistSnapshots(
            Simulation simulation,
            int year,
            List<SimulationResultDTO> scenarios
    ) {
        for (SimulationResultDTO dto : scenarios) {

            ProbabilitySnapshot snap = new ProbabilitySnapshot();
            snap.setSimulation(simulation);
            snap.setYear(year);

            snap.setP10(dto.p10);
            snap.setP50(dto.p50);
            snap.setP90(dto.p90);

            snap.setPercentile10(dto.p10);
            snap.setPercentile50(dto.p50);
            snap.setPercentile90(dto.p90);

            repo.save(snap);
        }
    }
}
