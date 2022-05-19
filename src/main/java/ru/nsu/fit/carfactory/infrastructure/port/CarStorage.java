package ru.nsu.fit.carfactory.infrastructure.port;

public interface CarStorage<Car> {
    int getMaxSize();
    int getNumProducts();
    void store(Car car);
    int getNumProductsWhenStorageChanged();
    Car consign();
}
