package ru.nsu.fit.carfactory.infrastructure.controller.impl;

import ru.nsu.fit.carfactory.application.usecase.OrderCarBuilding;
import ru.nsu.fit.carfactory.domain.Car;
import ru.nsu.fit.carfactory.infrastructure.controller.CarStorageController;
import ru.nsu.fit.carfactory.infrastructure.port.CarStorage;

public class CarStorageControllerImpl implements CarStorageController {
    private final CarStorage<Car> storage;
    private final OrderCarBuilding orderMoreCar;
    private boolean isWork;

    public CarStorageControllerImpl(CarStorage<Car> storage, OrderCarBuilding orderMoreCar) {
        this.storage = storage;
        this.orderMoreCar = orderMoreCar;
    }


    @Override
    public void run() {
        isWork = true;
        while (isWork){
            int numProducts = storage.getNumProductsWhenStorageChanged();
            int capacity = storage.getMaxSize();
            if((numProducts == 0) || (double)capacity/numProducts < 0.2)
                orderMoreCar.execute();
        }
    }

    public void end(){
        isWork = false;
    }
}
