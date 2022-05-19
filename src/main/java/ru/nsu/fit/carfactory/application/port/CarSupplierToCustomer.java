package ru.nsu.fit.carfactory.application.port;

import ru.nsu.fit.carfactory.domain.Car;

public interface CarSupplierToCustomer {
    void supply(Car car);
}
