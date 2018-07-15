package p11_threeuple;

public class Tuple<item1, item2> { //can't know what the variables are, but since they are coming from the console as input
    //they can be treated like strings
    private item1 variable1;
    private item2 variable2;

    public Tuple(item1 variable1, item2 variable2) {
        this.variable1 = variable1;
        this.variable2 = variable2;
    }

    public item1 getVariable1() {
        return this.variable1;
    }

    public void setVariable1(item1 variable1) {
        this.variable1 = variable1;
    }

    public item2 getVariable2() {
        return this.variable2;
    }

    public void setVariable2(item2 variable2) {
        this.variable2 = variable2;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.variable1, this.variable2);
    }
}
