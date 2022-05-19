package ru.nsu.fit.carfactory.application.model;

import ru.nsu.fit.carfactory.application.port.FactoryInfoOut;
import ru.nsu.fit.carfactory.domain.StorageType;

public interface FactoryInfo {
    void updateStorageInfo(StorageType type, int numOfProducts);
    void updateSoldInfo(int numOfSold);
    void addInfoOut(FactoryInfoOut out);
}
