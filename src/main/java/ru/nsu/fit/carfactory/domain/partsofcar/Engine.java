package ru.nsu.fit.carfactory.domain.partsofcar;

import java.util.UUID;

public class Engine implements SparePart{
    private final UUID id;

    private Engine(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static Engine build(){
        return new Engine(UUID.randomUUID());
    }
}
