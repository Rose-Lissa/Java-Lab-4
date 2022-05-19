package ru.nsu.fit.carfactory.storage;

import ru.nsu.fit.carfactory.domain.Car;

public class SimpleCustomer {
    int numOfBought = 0;

    public synchronized void buyCar(Car car){
        numOfBought++;
    }
}
