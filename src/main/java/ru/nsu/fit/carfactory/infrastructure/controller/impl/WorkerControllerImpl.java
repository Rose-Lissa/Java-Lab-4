package ru.nsu.fit.carfactory.infrastructure.controller.impl;


import ru.nsu.fit.carfactory.application.usecase.BuildCarAndSupplyToStorage;
import ru.nsu.fit.carfactory.domain.Car;
import ru.nsu.fit.carfactory.infrastructure.controller.WorkerController;
import ru.nsu.fit.carfactory.infrastructure.port.CarStorage;

public class WorkerControllerImpl implements WorkerController {
    private final BuildCarAndSupplyToStorage work;

    public WorkerControllerImpl(BuildCarAndSupplyToStorage work) {
        this.work = work;
    }

    @Override
    public void run() {
        work.execute();
    }

}
