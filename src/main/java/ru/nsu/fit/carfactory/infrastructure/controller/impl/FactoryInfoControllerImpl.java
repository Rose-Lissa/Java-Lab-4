package ru.nsu.fit.carfactory.infrastructure.controller.impl;

import ru.nsu.fit.carfactory.application.model.FactoryInfo;
import ru.nsu.fit.carfactory.application.usecase.ChangeTimeout;
import ru.nsu.fit.carfactory.domain.StorageType;
import ru.nsu.fit.carfactory.infrastructure.controller.FactoryInfoController;

public class FactoryInfoControllerImpl implements FactoryInfoController {
    private final FactoryInfo info;
    private final ChangeTimeout supplierTimeoutChange;
    private final ChangeTimeout dealerTimeoutChange;

    public FactoryInfoControllerImpl(FactoryInfo info, ChangeTimeout supplierTimeoutChange, ChangeTimeout dealerTimeoutChange) {
        this.info = info;
        this.supplierTimeoutChange = supplierTimeoutChange;
        this.dealerTimeoutChange = dealerTimeoutChange;
    }

    @Override
    public void updateStorageInfo(StorageType type, int numOfProducts) {
        info.updateStorageInfo(type, numOfProducts);
    }

    @Override
    public void updateSoldInfo(int numOfSold) {
        info.updateSoldInfo(numOfSold);
    }

    @Override
    public void changeSuppliersTimeoutPerMilliSec(int suppliersTimeoutPerMilliSec) {
        supplierTimeoutChange.execute(suppliersTimeoutPerMilliSec);
    }

    @Override
    public void changeDealersTimeoutPerMilliSec(int dealersTimeoutPerMilliSec) {
        dealerTimeoutChange.execute(dealersTimeoutPerMilliSec);
    }
}
