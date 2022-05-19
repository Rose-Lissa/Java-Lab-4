package ru.nsu.fit.carfactory.application.usecase.impl;

import ru.nsu.fit.carfactory.application.port.WorkerTeam;
import ru.nsu.fit.carfactory.application.usecase.OrderCarBuilding;

public class OrderCarBuildingImpl implements OrderCarBuilding {
    private final WorkerTeam workers;

    public OrderCarBuildingImpl(WorkerTeam workers) {
        this.workers = workers;
    }

    @Override
    public void execute() {
        workers.orderCars();
    }
}
