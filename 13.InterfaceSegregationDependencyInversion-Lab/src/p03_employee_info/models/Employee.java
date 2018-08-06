package p03_employee_info.models;

import p03_employee_info.abstract_classes.BaseEmployee;

public class Employee extends BaseEmployee {

    public Employee(String name, int salary) {
        super(name, salary);
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getSalary();
    }
}
