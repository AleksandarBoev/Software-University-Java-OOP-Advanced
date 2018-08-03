package comandPattern;

public abstract class BaseCommand implements Executable {
    private Car car;
    //LEARN DEPENDENCY INJECTOR HOW IT WORKS, because different classes having
    //different constructors kinda breaks the logic of the command pattern
    public BaseCommand(Car car) {
        this.car = car;
    }

    protected Car getCar() {
        return this.car;
    }
}
