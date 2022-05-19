package ru.nsu.fit.carfactory.application.port;

import ru.nsu.fit.carfactory.domain.partsofcar.SparePart;

public interface SparePartsConsigneeFromOutside<T extends SparePart> {
    T getSparePart();
}
