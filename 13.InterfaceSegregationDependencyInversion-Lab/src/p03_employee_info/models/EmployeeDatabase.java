package p03_employee_info.models;

import p03_employee_info.contracts.EmployeeDatabaseInterface;
import p03_employee_info.contracts.EmployeeInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeDatabase implements EmployeeDatabaseInterface {

    @Override
    public List<EmployeeInterface> readEmployees() {
        List<EmployeeInterface> employees = new ArrayList<>();
        Collections.addAll(employees,
                new Employee("Pesho", 20),
                new Employee("Gosho", 40));

        return employees;
    }
}
