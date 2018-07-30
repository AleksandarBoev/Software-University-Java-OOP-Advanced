package practise.engine;

import practise.contracts.CarFactory;
import practise.contracts.Repository;

public class EngineImpl {
    private CarFactory carFactory;
    private Repository carRepository;

    public EngineImpl(CarFactory carFactory, Repository carRepository) {
        this.carFactory = carFactory;
        this.carRepository = carRepository;
    }
}
