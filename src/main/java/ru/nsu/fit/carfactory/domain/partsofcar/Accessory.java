package ru.nsu.fit.carfactory.domain.partsofcar;

import java.util.UUID;

public class Accessory implements SparePart{
    private final UUID id;

    private Accessory(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static Accessory build(){
        return new Accessory(UUID.randomUUID());
    }
}
