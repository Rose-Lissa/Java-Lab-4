package ru.nsu.fit.carfactory.application.usecase.impl;

import ru.nsu.fit.carfactory.application.port.CarConsigneeFromStorage;
import ru.nsu.fit.carfactory.application.port.CarSupplierToCustomer;
import ru.nsu.fit.carfactory.application.usecase.DealCar;
import ru.nsu.fit.carfactory.domain.Car;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DealCarImpl implements DealCar {
    private final Logger logger = LogManager.getLogger(DealCarImpl.class);
    private final CarConsigneeFromStorage carConsignee;
    private final CarSupplierToCustomer carSupplier;

    public DealCarImpl(CarConsigneeFromStorage carConsignee,
                       CarSupplierToCustomer carSupplier) {
        this.carConsignee = carConsignee;
        this.carSupplier = carSupplier;
    }

    @Override
    public void execute(){
        Car car = carConsignee.consignCar();
        logger.log(Level.INFO, "Dealer<"+ Thread.currentThread().getName() +
                ">: Auto <" + car.getId() +"> (Body: <"+ car.getBody().getId() +">, Engine: <"+car.getEngine().getId()+">, Accessory: <"+car.getAccessory().getId()+">");
        //TODO log (<Time>: Dealer <Number>: Auto <ID> (Body: <ID>, Motor: <ID>, Accessory: <ID>)
        carSupplier.supply(car);
    }
}
