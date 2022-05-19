package ru.nsu.fit.carfactory.application.port;

import ru.nsu.fit.carfactory.domain.partsofcar.SparePart;

public interface SparePartsSupplierToStorage<T extends SparePart> {
    void supplySparePart(T sparePart);
}
