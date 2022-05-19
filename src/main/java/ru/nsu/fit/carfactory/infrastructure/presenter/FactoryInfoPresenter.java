package ru.nsu.fit.carfactory.infrastructure.presenter;

import ru.nsu.fit.carfactory.application.port.FactoryInfoOut;
import ru.nsu.fit.carfactory.infrastructure.port.FactoryInfoView;

public class FactoryInfoPresenter implements FactoryInfoOut {
    private final FactoryInfoView view;

    public FactoryInfoPresenter(FactoryInfoView view) {
        this.view = view;
    }

    public void present(int nEngines,
                        int nAccessories,
                        int nCarBodies,
                        int nCars,
                        int soldCars){
        view.setNAccessories(nAccessories);
        view.setNCarBodies(nCarBodies);
        view.setNCars(nCars);
        view.setNEngines(nEngines);
        view.setNSoldCars(soldCars);
    }
}
