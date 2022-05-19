package ru.nsu.fit.carfactory.infrastructure.repository;

import ru.nsu.fit.carfactory.application.model.FactoryInfo;
import ru.nsu.fit.carfactory.application.port.CarConsigneeFromStorage;
import ru.nsu.fit.carfactory.application.port.CarSupplierToStorage;
import ru.nsu.fit.carfactory.domain.Car;
import ru.nsu.fit.carfactory.domain.StorageType;
import ru.nsu.fit.carfactory.infrastructure.port.CarStorage;

public class CarRepository implements CarSupplierToStorage, CarConsigneeFromStorage {
    private final CarStorage<Car> storage;
    private final FactoryInfo info;

    public CarRepository(FactoryInfo info, CarStorage<Car> storage) {
        this.info = info;
        this.storage = storage;
    }

    @Override
    public void supplyCar(Car car) {
        storage.store(car);
        info.updateStorageInfo(StorageType.CAR, storage.getNumProducts());
    }

    @Override
    public Car consignCar() {
        Car car = storage.consign();
        info.updateStorageInfo(StorageType.CAR, storage.getNumProducts());
        return car;
    }
}
