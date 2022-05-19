package ru.nsu.fit.carfactory.threadfactory;

import ru.nsu.fit.carfactory.infrastructure.controller.DealerController;
import ru.nsu.fit.carfactory.infrastructure.port.PersonalFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DealersThreadFactory implements PersonalFactory {
    private final List<Thread> threads;
    DealerController dealer;
    private static int i = 0;

    public DealersThreadFactory(DealerController dealer, int numThreads) {
        this.dealer = dealer;
        threads = Stream.generate(() ->{
                    i++;
                    return new Thread(dealer, "dealer-" + i);
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
        dealer.end();
    }
}
