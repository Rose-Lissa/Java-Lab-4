package ru.nsu.fit.carfactory.infrastructure.getaway;

import ru.nsu.fit.carfactory.application.port.SparePartsConsigneeFromOutside;
import ru.nsu.fit.carfactory.domain.partsofcar.Accessory;

public class AccessorySupplierGetaway
        implements SparePartsConsigneeFromOutside<Accessory> {
    @Override
    public Accessory getSparePart() {
        return Accessory.build();
    }
}
