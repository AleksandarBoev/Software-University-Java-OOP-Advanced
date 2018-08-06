package p03_employee_info.models;

import p03_employee_info.contracts.EmployeeInterface;
import p03_employee_info.contracts.Formatter;

public class ConsoleFormatter implements Formatter<EmployeeInterface> {

    @Override
    public String format(Iterable<EmployeeInterface> employees) {
        StringBuilder sb = new StringBuilder();
        for (EmployeeInterface employee : employees) {
            sb.append(employee).append(System.lineSeparator());
        }

        return sb.toString().substring(0, sb.length() - 1); //changed so it wouldn't have the last new line
    }
}
