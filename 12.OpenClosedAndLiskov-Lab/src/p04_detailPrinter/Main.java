package p04_detailPrinter;

import p04_detailPrinter.workers.Employee;
import p04_detailPrinter.workers.Manager;
import p04_detailPrinter.workers.Printable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Printable employee = new Employee("Pesho");

        Printable manager = new Manager("Gosho", Arrays.asList("Document1", "Doc2", "Top Secret3"));

        Iterable<Printable> workers = Arrays.asList(employee, manager);
        DetailsPrinter detailsPrinter = new DetailsPrinter(workers);

        detailsPrinter.printDetails();
    }
}
