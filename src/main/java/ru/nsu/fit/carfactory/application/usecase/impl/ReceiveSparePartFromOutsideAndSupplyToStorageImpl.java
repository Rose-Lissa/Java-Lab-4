package ru.nsu.fit.carfactory.application.usecase.impl;

import ru.nsu.fit.carfactory.application.port.SparePartsConsigneeFromOutside;
import ru.nsu.fit.carfactory.application.port.SparePartsSupplierToStorage;
import ru.nsu.fit.carfactory.application.usecase.ReceiveSparePartFromOutsideAndSupplyToStorage;
import ru.nsu.fit.carfactory.domain.partsofcar.SparePart;

public class ReceiveSparePartFromOutsideAndSupplyToStorageImpl<T extends SparePart> implements ReceiveSparePartFromOutsideAndSupplyToStorage {
    private final SparePartsConsigneeFromOutside<T> from;
    private final SparePartsSupplierToStorage<T> to;

    public ReceiveSparePartFromOutsideAndSupplyToStorageImpl
            (SparePartsConsigneeFromOutside<T> from,
             SparePartsSupplierToStorage<T> to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(){
        T sparePart = from.getSparePart();
        to.supplySparePart(sparePart);
    }
}
