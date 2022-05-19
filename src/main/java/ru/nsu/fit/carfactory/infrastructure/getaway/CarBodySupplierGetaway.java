package ru.nsu.fit.carfactory.infrastructure.getaway;

import ru.nsu.fit.carfactory.application.port.SparePartsConsigneeFromOutside;
import ru.nsu.fit.carfactory.domain.partsofcar.CarBody;

public class CarBodySupplierGetaway implements SparePartsConsigneeFromOutside<CarBody> {

    @Override
    public CarBody getSparePart() {
        return CarBody.build();
    }
}
