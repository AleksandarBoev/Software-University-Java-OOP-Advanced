package p04_detailPrinter;

import p04_detailPrinter.workers.Printable;

public class DetailsPrinter {

    private Iterable<Printable> employees;

    public DetailsPrinter(Iterable<Printable> employees) {
        this.employees = employees;
    }

    public void printDetails() {
        for (Printable employee : employees) {
            System.out.println(employee.toString());
        }
    }
}
