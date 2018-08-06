package p03_employee_info.models;

import p03_employee_info.contracts.ConsoleClientInterface;

public class ConsoleClient implements ConsoleClientInterface {
    @Override
    public void run() {
        EmployeeInfoProvider employeeInfo = new EmployeeInfoProvider();
        ConsoleFormatter formatter = new ConsoleFormatter();

        String output = formatter.format(employeeInfo.getEmployeesByName());
        System.out.println(output);
    }
}
