package ru.nsu.fit.carfactory.infrastructure.getaway;

import ru.nsu.fit.carfactory.application.port.SparePartsConsigneeFromOutside;
import ru.nsu.fit.carfactory.domain.partsofcar.Engine;

public class EngineSupplierGetaway implements SparePartsConsigneeFromOutside<Engine> {
    @Override
    public Engine getSparePart() {
        return Engine.build();
    }
}
