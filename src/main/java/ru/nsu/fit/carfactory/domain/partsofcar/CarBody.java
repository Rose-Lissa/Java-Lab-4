package ru.nsu.fit.carfactory.domain.partsofcar;

import java.util.UUID;

public class CarBody implements SparePart{
    private final UUID id;

    private CarBody(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static CarBody build(){
        return new CarBody(UUID.randomUUID());
    }
}
