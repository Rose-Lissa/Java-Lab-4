package ru.nsu.fit.carfactory.application.usecase;

import ru.nsu.fit.carfactory.domain.StorageType;

public interface UpdateFactoryStorageInfo {
    void execute(StorageType type, int numOfProducts);
}
