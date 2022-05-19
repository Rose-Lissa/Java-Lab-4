package ru.nsu.fit.carfactory.infrastructure.getaway;

import ru.nsu.fit.carfactory.application.model.FactoryInfo;
import ru.nsu.fit.carfactory.application.port.CarSupplierToCustomer;
import ru.nsu.fit.carfactory.domain.Car;

import java.util.concurrent.atomic.AtomicInteger;

public class CustomerGetaway implements CarSupplierToCustomer {
    private final FactoryInfo info;

    private static final AtomicInteger integer = new AtomicInteger(0);

    public CustomerGetaway(FactoryInfo info){
        this.info = info;
    }

    @Override
    public void supply(Car car) {
        info.updateSoldInfo(integer.incrementAndGet());
    }
}
