package p03_employee_info;

import p03_employee_info.contracts.ConsoleClientInterface;
import p03_employee_info.models.ConsoleClient;
import p03_employee_info.models.ConsoleFormatter;
import p03_employee_info.models.EmployeeInfoProvider;

public class Main {

    public static void main(String[] args) {
        ConsoleClientInterface consoleClient = new ConsoleClient();
        consoleClient.run();
    }
}
