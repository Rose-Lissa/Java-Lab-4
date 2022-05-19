package ru.nsu.fit.carfactory.application.usecase.impl;

import ru.nsu.fit.carfactory.application.port.PersonalWithTimeout;
import ru.nsu.fit.carfactory.application.usecase.ChangeTimeout;

import java.util.List;

public class ChangeTimeoutImpl implements ChangeTimeout {
    List<PersonalWithTimeout> personal;

    public ChangeTimeoutImpl(List<PersonalWithTimeout> personal) {
        this.personal = personal;
    }

    @Override
    public void execute(int timeout) {
        for (var person: personal) {
            person.setTimeout(timeout);
        };
    }
}
