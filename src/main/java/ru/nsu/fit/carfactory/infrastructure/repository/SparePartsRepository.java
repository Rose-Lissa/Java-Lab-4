package ru.nsu.fit.carfactory.infrastructure.repository;

import ru.nsu.fit.carfactory.application.model.FactoryInfo;
import ru.nsu.fit.carfactory.application.port.SparePartsSupplierToStorage;
import ru.nsu.fit.carfactory.application.port.SparePartsConsigneeFromStorage;
import ru.nsu.fit.carfactory.domain.StorageType;
import ru.nsu.fit.carfactory.domain.partsofcar.Accessory;
import ru.nsu.fit.carfactory.domain.partsofcar.CarBody;
import ru.nsu.fit.carfactory.domain.partsofcar.Engine;
import ru.nsu.fit.carfactory.domain.partsofcar.SparePart;
import ru.nsu.fit.carfactory.infrastructure.port.SparePartsStorage;

public class SparePartsRepository<T extends SparePart>
        implements SparePartsSupplierToStorage<T>, SparePartsConsigneeFromStorage<T> {
    SparePartsStorage<T> storage;
    private final FactoryInfo info;

    public SparePartsRepository(FactoryInfo info,
                                SparePartsStorage<T> storage) {
        this.storage = storage;
        this.info = info;
    }

    @Override
    public void supplySparePart(T sparePart) {
        storage.store(sparePart);
        updateModel(sparePart);
    }

    @Override
    public T consign() {
        var sparePart = storage.consign();
        updateModel(sparePart);
        return sparePart;
    }

    private void updateModel(T sparePart){
        if(sparePart instanceof Engine){
            info.updateStorageInfo(StorageType.ENGINE, storage.getNumProducts());
        }
        else if(sparePart instanceof Accessory){
            info.updateStorageInfo(StorageType.ACCESSORY, storage.getNumProducts());
        } else if(sparePart instanceof CarBody){
            info.updateStorageInfo(StorageType.CAR_BODY, storage.getNumProducts());
        }
    }
}
