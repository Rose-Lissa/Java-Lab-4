package ru.nsu.fit.carfactory.infrastructure.controller.impl;

import ru.nsu.fit.carfactory.application.port.PersonalWithTimeout;
import ru.nsu.fit.carfactory.application.usecase.DealCar;
import ru.nsu.fit.carfactory.infrastructure.controller.DealerController;

public class DealerControllerImpl implements DealerController, PersonalWithTimeout {
    private final DealCar deal;
    private volatile boolean isWork;
    private volatile int timeOutPerMilliSec;

    public DealerControllerImpl(DealCar deal, int timeoutPerMilliSec) {
        this.deal = deal;
        this.timeOutPerMilliSec = timeoutPerMilliSec;
    }

    @Override
    public void run() {
        isWork = true;
        while (isWork) {
            deal.execute();
            try {
                Thread.sleep(timeOutPerMilliSec);
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
        this.timeOutPerMilliSec = timeoutPerMilliSec;
    }
}
