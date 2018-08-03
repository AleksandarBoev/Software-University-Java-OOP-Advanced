package comandPattern;

public class CalculateConditionCommand extends BaseCommand {
    public CalculateConditionCommand(Car car) {
        super(car);
    }

    @Override
    public void execute() {
        super.getCar().calculateCondition();
    }
}
