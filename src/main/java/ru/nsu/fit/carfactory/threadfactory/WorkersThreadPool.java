package ru.nsu.fit.carfactory.threadfactory;

import ru.nsu.fit.carfactory.infrastructure.controller.WorkerController;
import ru.nsu.fit.carfactory.infrastructure.port.PersonalFactory;
import ru.nsu.fit.carfactory.infrastructure.port.WorkerTeam;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkersThreadPool implements PersonalFactory, WorkerTeam {
    private final ExecutorService executor;
    private final int numThreads;
    private final WorkerController worker;

    public WorkersThreadPool(WorkerController worker, int numThreads) {
        this.executor = Executors.newFixedThreadPool(numThreads);
        this.numThreads = numThreads;
        this.worker = worker;
    }

    public void makeMoreCars(){
        startJob();
    }

    public void startJob() {
        for(int i = 0; i < numThreads; i++){
            executor.submit(worker);
        }
    }

    public void endJob(){
        executor.shutdown();
    }
}
