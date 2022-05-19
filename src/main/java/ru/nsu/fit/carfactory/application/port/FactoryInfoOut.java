package ru.nsu.fit.carfactory.application.port;

public interface FactoryInfoOut {
    void present(int nEngines,
                 int nAccessories,
                 int nCarBodies,
                 int nCars,
                 int soldCars);
}
