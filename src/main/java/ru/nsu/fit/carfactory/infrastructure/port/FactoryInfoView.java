package ru.nsu.fit.carfactory.infrastructure.port;

public interface FactoryInfoView {
    void setNAccessories(int n);
    void setNEngines(int n);
    void setNCarBodies(int n);
    void setNCars(int n);
    void setNSoldCars(int n);
}
