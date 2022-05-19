package ru.nsu.fit.carfactory.application.port;

import ru.nsu.fit.carfactory.domain.partsofcar.SparePart;

public interface SparePartsConsigneeFromStorage<T extends SparePart> {
    T consign();
}
