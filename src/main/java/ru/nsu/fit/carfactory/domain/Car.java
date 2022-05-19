package ru.nsu.fit.carfactory.domain;

import ru.nsu.fit.carfactory.domain.partsofcar.Accessory;
import ru.nsu.fit.carfactory.domain.partsofcar.CarBody;
import ru.nsu.fit.carfactory.domain.partsofcar.Engine;

import java.util.UUID;


public class Car {
    private final UUID id;

    private final CarBody body;
    private final Engine engine;
    private final Accessory accessory;

    private Car(UUID id,
                Engine engine,
                CarBody body,
                Accessory accessory) {
        this.id = id;
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
    }


    public static Car buildFromSpareParts(Engine engine,
                                          CarBody body,
                                          Accessory accessory) {
        return new Car(UUID.randomUUID(),
                engine,
                body,
                accessory);
    }

    public UUID getId() {
        return id;
    }

    public CarBody getBody() {
        return body;
    }

    public Engine getEngine() {
        return engine;
    }

    public Accessory getAccessory() {
        return accessory;
    }
}
