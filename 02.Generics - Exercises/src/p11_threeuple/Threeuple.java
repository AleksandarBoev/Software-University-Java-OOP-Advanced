package p11_threeuple;

public class Threeuple<item1, item2, item3> extends Tuple {
    private item3 variable3;

    public Threeuple(item1 variable1, item2 variable2, item3 variable3) {
        super(variable1, variable2);
        this.variable3 = variable3;
    }

    public item3 getVariable3() {
        return this.variable3;
    }

    public void setVariable3(item3 variable3) {
        this.variable3 = variable3;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", super.toString(), this.variable3);
    }
}
