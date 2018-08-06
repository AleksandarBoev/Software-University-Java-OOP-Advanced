package p03_employee_info.contracts;

import p03_employee_info.models.Employee;

public interface Formatter<T> {
    String format(Iterable<T> elements);
}