package ru.nsu.fit.carfactory.infrastructure.port;

public interface SparePartsStorage<T>{
    void store(T sparePart);
    T consign();
    int getMaxSize();
    int getNumProducts();
}
