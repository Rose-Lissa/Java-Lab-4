package ru.nsu.fit.carfactory.storage;

import ru.nsu.fit.carfactory.infrastructure.port.CarStorage;
import ru.nsu.fit.carfactory.infrastructure.port.SparePartsStorage;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage<T> implements SparePartsStorage<T>, CarStorage<T> {
    private final ArrayDeque<T> storage;
    private final int maxSize;
    private boolean isChanged;
    private final Lock lock = new ReentrantLock();
    private final Condition consignCondition = lock.newCondition();
    private final Condition storeCondition = lock.newCondition();
    private final Condition storeChangedCondition = lock.newCondition();

    public Storage(int maxSize) {
        this.maxSize = maxSize;
        this.storage = new ArrayDeque<>();
    }

    @Override
    public void store(T sth){
        lock.lock();
        while (storage.size() >= maxSize){
            try {
                storeCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(sth);
        System.out.println(storage.size());
        isChanged = true;
        consignCondition.signal();
        storeChangedCondition.signal();
        lock.unlock();
    }
    @Override
    public T consign(){
        lock.lock();
        while (storage.isEmpty()){
            try {
                consignCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T sth = storage.pop();
        storeCondition.signal();
        isChanged = true;
        storeChangedCondition.signal();
        lock.unlock();
        return sth;
    }

    @Override
    public int getMaxSize(){
        return maxSize;
    }

    @Override
    public int getNumProductsWhenStorageChanged(){
        lock.lock();
        try {
            while (!isChanged)
                storeChangedCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int size = storage.size();
        isChanged = false;
        lock.unlock();
        return size;
    }

    @Override
    public int getNumProducts(){
        lock.lock();
        int size = storage.size();
        lock.unlock();
        return size;
    }
}
