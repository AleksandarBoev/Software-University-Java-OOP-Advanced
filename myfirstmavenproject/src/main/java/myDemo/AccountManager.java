package myDemo;

public class AccountManager {
    private String name;
    private double salary;
    private double savings;

    public AccountManager(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.savings = 0.0;
    }

    public double getSalary() {
        return this.salary;
    }

    public void getPaid(double amount) {
        this.savings += amount;
    }
}
