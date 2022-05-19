package ru.nsu.fit.carfactory.application.model;

import ru.nsu.fit.carfactory.application.port.FactoryInfoOut;
import ru.nsu.fit.carfactory.domain.StorageType;

import java.util.ArrayList;
import java.util.List;

public class FactoryInfoImpl implements FactoryInfo{
    private final List<FactoryInfoOut> presenters;

    private int numCarInStorage = 0;
    private int numCarBodyInStorage = 0;
    private int numEnginesInStorage = 0;

    private int numAccessoriesInStorage = 0;
    private int numSoldCar = 0;

    public FactoryInfoImpl(
            int suppliersTimeoutPerMilliSec,
            int dealersTimeoutPerMilliSec) {
        presenters = new ArrayList<>();
    }


    public void addInfoOut(FactoryInfoOut out){
        presenters.add(out);
    }

    private void notifyInfoOuts(){
        for(var presenter : presenters){
            presenter.present(
                    numEnginesInStorage,
                    numAccessoriesInStorage,
                    numCarBodyInStorage,
                    numCarInStorage,
                    numSoldCar);
        }
    }

    @Override
    public void updateStorageInfo(StorageType type, int numOfProducts) {
        switch (type){
            case ACCESSORY -> numAccessoriesInStorage = numOfProducts;
            case CAR -> numCarInStorage = numOfProducts;
            case ENGINE -> numEnginesInStorage = numOfProducts;
            case CAR_BODY -> numCarBodyInStorage = numOfProducts;
        }
        notifyInfoOuts();
    }

    @Override
    public void updateSoldInfo(int numOfSold) {
        numSoldCar = numOfSold;
        notifyInfoOuts();
    }
}
