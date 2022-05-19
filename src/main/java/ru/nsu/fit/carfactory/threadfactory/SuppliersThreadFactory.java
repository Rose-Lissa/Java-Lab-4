package ru.nsu.fit.carfactory.threadfactory;

import ru.nsu.fit.carfactory.infrastructure.controller.SupplierController;
import ru.nsu.fit.carfactory.infrastructure.port.PersonalFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuppliersThreadFactory implements PersonalFactory {
    private final List<Thread> threads;
    private final SupplierController supplier;
    private static int i = 0;

    public SuppliersThreadFactory(SupplierController supplier, int numThreads) {
        this.supplier = supplier;


        threads = Stream.generate(() ->{
            i++;
            return new Thread(supplier, "supplier-" + i);
        })
                .limit(numThreads)
                .collect(Collectors.toList());
    }

    public void startJob(){
        for (var thread: threads) {
            thread.start();
        }
    }

    public void endJob(){
        supplier.end();
    }
}
