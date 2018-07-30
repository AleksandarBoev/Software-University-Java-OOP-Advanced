package practise.commands;

import practise.contracts.CarFactory;
import practise.contracts.Executable;
import practise.contracts.Repository;

public abstract class BaseCommand implements Executable {
    private CarFactory carFactory;
    private Repository carRepository;
    private String[] data;


}
