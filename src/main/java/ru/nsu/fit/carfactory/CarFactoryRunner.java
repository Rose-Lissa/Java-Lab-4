package ru.nsu.fit.carfactory;


import ru.nsu.fit.carfactory.application.model.Configuration;
import ru.nsu.fit.carfactory.application.model.FactoryInfo;
import ru.nsu.fit.carfactory.application.model.FactoryInfoImpl;
import ru.nsu.fit.carfactory.application.usecase.impl.*;
import ru.nsu.fit.carfactory.domain.Car;
import ru.nsu.fit.carfactory.domain.partsofcar.Accessory;
import ru.nsu.fit.carfactory.domain.partsofcar.CarBody;
import ru.nsu.fit.carfactory.domain.partsofcar.Engine;
import ru.nsu.fit.carfactory.infrastructure.controller.*;
import ru.nsu.fit.carfactory.infrastructure.controller.impl.*;
import ru.nsu.fit.carfactory.infrastructure.getaway.*;
import ru.nsu.fit.carfactory.infrastructure.presenter.FactoryInfoPresenter;
import ru.nsu.fit.carfactory.infrastructure.repository.CarRepository;
import ru.nsu.fit.carfactory.infrastructure.repository.SparePartsRepository;
import ru.nsu.fit.carfactory.storage.Storage;
import ru.nsu.fit.carfactory.threadfactory.DealersThreadFactory;
import ru.nsu.fit.carfactory.threadfactory.SuppliersThreadFactory;
import ru.nsu.fit.carfactory.threadfactory.WorkersThreadPool;
import ru.nsu.fit.carfactory.view.FactoryView;

import java.io.IOException;
import java.util.List;

public class CarFactoryRunner {
    private static SuppliersThreadFactory enginesSuppliersThreadFactory;
    private static SuppliersThreadFactory carBodySuppliersThreadFactory;
    private static SuppliersThreadFactory accessorySuppliersThreadFactory;
    private static WorkersThreadPool workersThreadPool;
    private static DealersThreadFactory dealersThreadFactory;
    private static CarStorageController carStorageController;

    public static void main(String[] args) throws IOException {
        Configuration configuration = Configuration.getInit();
        System.out.println(configuration.getAccessorySuppliers());

        FactoryInfo model = new FactoryInfoImpl(
                configuration.getSuppliersTimeoutPerMillisecond(),
                configuration.getDealersTimeoutPerMillisecond());

        Storage<Engine> engineStorage = new Storage<>(configuration.getStorageEngineSize());
        Storage<Car> carStorage = new Storage<>(configuration.getStorageCarSize());
        Storage<CarBody> carBodyStorage = new Storage<>(configuration.getStorageCarBodySize());
        Storage<Accessory> accessoryStorage = new Storage<>(configuration.getStorageAccessorySize());

        SparePartsRepository<Engine> engineRepository = new SparePartsRepository<>(model, engineStorage);
        SparePartsRepository<CarBody> carBodyRepository = new SparePartsRepository<>(model, carBodyStorage);
        SparePartsRepository<Accessory> accessoryRepository = new SparePartsRepository<>(model, accessoryStorage);
        CarRepository carRepository = new CarRepository(model, carStorage);

        AccessorySupplierGetaway accessorySupplierGetaway = new AccessorySupplierGetaway();
        CarBodySupplierGetaway carBodySupplierGetaway = new CarBodySupplierGetaway();
        EngineSupplierGetaway engineSupplierGetaway = new EngineSupplierGetaway();
        CustomerGetaway customerGetaway = new CustomerGetaway(model);


        SupplierControllerImpl engineSupplier = new SupplierControllerImpl(
                new ReceiveSparePartFromOutsideAndSupplyToStorageImpl<>(
                        engineSupplierGetaway,
                        engineRepository),
                configuration.getSuppliersTimeoutPerMillisecond());
        SupplierControllerImpl accessorySupplier = new SupplierControllerImpl(
                new ReceiveSparePartFromOutsideAndSupplyToStorageImpl<>(
                        accessorySupplierGetaway,
                        accessoryRepository
                ),
                configuration.getSuppliersTimeoutPerMillisecond());
        SupplierControllerImpl carBodySupplier = new SupplierControllerImpl(
                new ReceiveSparePartFromOutsideAndSupplyToStorageImpl<>(
                        carBodySupplierGetaway,
                        carBodyRepository),
                configuration.getSuppliersTimeoutPerMillisecond());

        WorkerController worker = new WorkerControllerImpl(
                new BuildCarAndSupplyToStorageImpl(
                        engineRepository,
                        carBodyRepository,
                        accessoryRepository,
                        carRepository));

        DealerControllerImpl dealer = new DealerControllerImpl(
                new DealCarImpl(carRepository, customerGetaway),
                configuration.getDealersTimeoutPerMillisecond());

        enginesSuppliersThreadFactory = new SuppliersThreadFactory(
                engineSupplier,
                configuration.getEngineSuppliers());

        carBodySuppliersThreadFactory = new SuppliersThreadFactory(
                carBodySupplier,
                configuration.getCarBodySuppliers());

        accessorySuppliersThreadFactory = new SuppliersThreadFactory(
                accessorySupplier,
                configuration.getAccessorySuppliers());

        workersThreadPool = new WorkersThreadPool(
                worker,
                configuration.getWorkers());

        dealersThreadFactory = new DealersThreadFactory(
                dealer,
                configuration.getDealers());

        WorkersTeamGetaway workersTeamGetaway = new WorkersTeamGetaway(workersThreadPool);

        carStorageController = new CarStorageControllerImpl(
                carStorage,
                new OrderCarBuildingImpl(workersTeamGetaway));


        FactoryInfoController controller = new FactoryInfoControllerImpl(
                model,
                new ChangeTimeoutImpl(List.of(engineSupplier, carBodySupplier, accessorySupplier)),
                new ChangeTimeoutImpl(List.of(dealer)));
        FactoryView view = new FactoryView(controller);
        FactoryInfoPresenter infoPresenter = new FactoryInfoPresenter(view);
        model.addInfoOut(infoPresenter);
        view.setVisible(true);
        startJob();
    }

    private static void startJob(){
        enginesSuppliersThreadFactory.startJob();
        carBodySuppliersThreadFactory.startJob();
        accessorySuppliersThreadFactory.startJob();
        workersThreadPool.startJob();
        dealersThreadFactory.startJob();

        Thread carStorageControllerThread = new Thread(carStorageController, "controller");
        carStorageControllerThread.start();
    }

    public static void endJob(){
        enginesSuppliersThreadFactory.endJob();
        carBodySuppliersThreadFactory.endJob();
        accessorySuppliersThreadFactory.endJob();
        workersThreadPool.endJob();
        dealersThreadFactory.endJob();
        carStorageController.end();
    }
}