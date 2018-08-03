package comandPattern;

public class DriveCommand extends BaseCommand {
    private double distance;

    public DriveCommand(Car car, double distance) {
        super(car);
        this.distance = distance;
    }

    @Override
    public void execute() {
        super.getCar().drive(this.distance);
    }
}
