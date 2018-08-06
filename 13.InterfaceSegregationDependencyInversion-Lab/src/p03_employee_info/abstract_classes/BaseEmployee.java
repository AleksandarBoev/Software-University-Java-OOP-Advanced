package p03_employee_info.abstract_classes;

import p03_employee_info.contracts.EmployeeInterface;

public abstract class BaseEmployee implements EmployeeInterface {
    private String name;
    private int salary;

    public BaseEmployee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() { //overriding in the abstract class, because they are getters and it is unlikely they will
        return this.name;    //have different implementations in the next classes
    }

    @Override
    public int getSalary() {
        return this.salary;
    }
}
