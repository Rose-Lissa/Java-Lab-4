package ru.nsu.fit.carfactory.infrastructure.controller.impl;

import ru.nsu.fit.carfactory.application.port.PersonalWithTimeout;
import ru.nsu.fit.carfactory.application.usecase.ReceiveSparePartFromOutsideAndSupplyToStorage;
import ru.nsu.fit.carfactory.infrastructure.controller.SupplierController;

public class SupplierControllerImpl implements SupplierController, PersonalWithTimeout {
    private final ReceiveSparePartFromOutsideAndSupplyToStorage supplier;
    private volatile boolean isWork;
    private volatile int timeoutPerMilliSec;

    public SupplierControllerImpl(
            ReceiveSparePartFromOutsideAndSupplyToStorage supplier,
            int timeoutPerSec) {
        this.supplier = supplier;
        this.timeoutPerMilliSec = timeoutPerSec;
    }

    @Override
    public void run() {
        isWork = true;
        while (isWork) {
            supplier.execute();
            try {
                Thread.sleep(timeoutPerMilliSec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void end(){
        isWork = false;
    }

    @Override
    public void setTimeout(int timeoutPerMilliSec) {
        this.timeoutPerMilliSec = timeoutPerMilliSec;
    }
}
