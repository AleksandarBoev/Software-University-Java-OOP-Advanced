package p04_detailPrinter.workers;

public abstract class BaseEmployee implements Printable {
    private String name;

    protected BaseEmployee(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Name: %s", this.name);
    }
}
