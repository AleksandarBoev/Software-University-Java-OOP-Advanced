package p04_detailPrinter.workers;

public class Manager extends BaseEmployee {

    private Iterable<String> documents;

    public Manager(String name, Iterable<String> documents) {
        super(name);
        this.documents = documents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString()).append(", ").append("Documents: ");
        sb.append(String.join(", ", this.documents));

        return sb.toString();
    }
}
