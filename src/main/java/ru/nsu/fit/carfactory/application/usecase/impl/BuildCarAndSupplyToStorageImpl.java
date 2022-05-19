package ru.nsu.fit.carfactory.application.usecase.impl;

import ru.nsu.fit.carfactory.application.port.CarSupplierToStorage;
import ru.nsu.fit.carfactory.application.port.SparePartsConsigneeFromStorage;
import ru.nsu.fit.carfactory.application.usecase.BuildCarAndSupplyToStorage;
import ru.nsu.fit.carfactory.application.usecase.UseCase;
import ru.nsu.fit.carfactory.domain.Car;
import ru.nsu.fit.carfactory.domain.partsofcar.Accessory;
import ru.nsu.fit.carfactory.domain.partsofcar.CarBody;
import ru.nsu.fit.carfactory.domain.partsofcar.Engine;

public class BuildCarAndSupplyToStorageImpl implements BuildCarAndSupplyToStorage {
    SparePartsConsigneeFromStorage<Engine> engineConsignee;
    SparePartsConsigneeFromStorage<CarBody> carBodyConsignee;
    SparePartsConsigneeFromStorage<Accessory> accessoryConsignee;
    CarSupplierToStorage carSupplier;

    public BuildCarAndSupplyToStorageImpl(SparePartsConsigneeFromStorage<Engine> engineConsignee,
                                          SparePartsConsigneeFromStorage<CarBody> carBodyConsignee,
                                          SparePartsConsigneeFromStorage<Accessory> accessoryConsignee,
                                          CarSupplierToStorage carSupplier) {
        this.engineConsignee = engineConsignee;
        this.carBodyConsignee = carBodyConsignee;
        this.accessoryConsignee = accessoryConsignee;
        this.carSupplier = carSupplier;
    }

    @Override
    public void execute(){
        Engine engine = engineConsignee.consign();
        CarBody body = carBodyConsignee.consign();
        Accessory accessory = accessoryConsignee.consign();

        Car car = Car.buildFromSpareParts(engine, body, accessory);

        carSupplier.supplyCar(car);
    }
}
