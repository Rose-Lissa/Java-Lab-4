package ru.nsu.fit.carfactory.application.model;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Configuration init;
    Properties properties;

    private int storageCarBodySize;
    private int storageEngineSize;
    private int storageAccessorySize;
    private int storageCarSize;
    private int accessorySuppliers;
    private int engineSuppliers;
    private int carBodySuppliers;
    private int workers;
    private int dealers;
    private int suppliersTimeoutPerMillisecond;
    private int dealersTimeoutPerMillisecond;
    private boolean logSale;

    private Configuration(Properties properties){
        this.properties = properties;
        storageCarBodySize = Integer.parseInt(properties.getProperty("StorageCarBodySize"));
        storageEngineSize = Integer.parseInt(properties.getProperty("StorageEngineSize"));
        storageAccessorySize = Integer.parseInt(properties.getProperty("StorageAccessorySize"));
        storageCarSize = Integer.parseInt(properties.getProperty("StorageCarSize"));
        accessorySuppliers = Integer.parseInt(properties.getProperty("AccessorySuppliers"));
        engineSuppliers = Integer.parseInt(properties.getProperty("EngineSuppliers"));
        carBodySuppliers = Integer.parseInt(properties.getProperty("CarBodySuppliers"));
        workers = Integer.parseInt(properties.getProperty("Workers"));
        dealers = Integer.parseInt(properties.getProperty("Dealers"));
        logSale = Boolean.getBoolean("LogSale");
        suppliersTimeoutPerMillisecond = Integer.parseInt(properties.getProperty("SuppliersTimeout"));
        dealersTimeoutPerMillisecond = Integer.parseInt(properties.getProperty("DealersTimeout"));
    }

    public static Configuration getInit() throws IOException {
        if(init == null){
            Properties properties = new Properties();
            properties.load(Configuration.class.getClassLoader().getResourceAsStream("factory.properties"));
            init = new Configuration(properties);
            return init;
        }
        return init;
    }

    public int getStorageCarBodySize() {
        return storageCarBodySize;
    }

    public int getStorageEngineSize() {
        return storageEngineSize;
    }

    public int getStorageAccessorySize() {
        return storageAccessorySize;
    }

    public int getStorageCarSize() {
        return storageCarSize;
    }

    public int getAccessorySuppliers() {
        return accessorySuppliers;
    }

    public int getEngineSuppliers() {
        return engineSuppliers;
    }

    public int getCarBodySuppliers() {
        return carBodySuppliers;
    }

    public int getWorkers() {
        return workers;
    }

    public int getDealers() {
        return dealers;
    }

    public boolean isLogSale() {
        return logSale;
    }


    public int getSuppliersTimeoutPerMillisecond() {
        return suppliersTimeoutPerMillisecond;
    }

    public int getDealersTimeoutPerMillisecond() {
        return dealersTimeoutPerMillisecond;
    }
}
