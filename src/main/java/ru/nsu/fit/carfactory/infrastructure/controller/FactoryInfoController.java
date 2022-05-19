package ru.nsu.fit.carfactory.infrastructure.controller;

import ru.nsu.fit.carfactory.domain.StorageType;

public interface FactoryInfoController {
    void updateStorageInfo(StorageType type, int numOfProducts);
    void updateSoldInfo(int numOfSold);
    void changeSuppliersTimeoutPerMilliSec(int suppliersTimeoutPerMilliSec);
    void changeDealersTimeoutPerMilliSec(int dealersTimeoutPerMilliSec);
}

