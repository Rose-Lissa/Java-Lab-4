package ru.nsu.fit.carfactory.infrastructure.getaway;

import ru.nsu.fit.carfactory.application.port.WorkerTeam;

public class WorkersTeamGetaway implements WorkerTeam {
    private final ru.nsu.fit.carfactory.infrastructure.port.WorkerTeam workers;

    public WorkersTeamGetaway(ru.nsu.fit.carfactory.infrastructure.port.WorkerTeam workers) {
        this.workers = workers;
    }

    @Override
    public void orderCars() {
        workers.makeMoreCars();
    }
}
